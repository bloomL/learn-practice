package mapper;

import model.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Create by liguo on 2021/12/8
 **/

public class StudentMapperTest {

    private SqlSessionFactory factory;

    @Before
    public void setUp() throws IOException {
        factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
    }

    @Test
    public void showDefaultCacheConfiguration() {
        System.out.println("本地缓存范围: " + factory.getConfiguration().getLocalCacheScope());
        System.out.println("二级缓存是否被启用: " + factory.getConfiguration().isCacheEnabled());
    }

    @Test
    public void testLocalCache() {
        // 自动提交事务
        SqlSession sqlSession = factory.openSession(true);
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        //同一会话，查询条件相同，走缓存，只有一次查询数据库
        System.out.println(studentMapper.getStudentById(1));
        System.out.println(studentMapper.getStudentById(1));
        System.out.println(studentMapper.getStudentById(1));

        sqlSession.close();
    }

    /**
     * 一次数据库会话中，如果数据库发生修改，一级缓存是否会失效
     * result: 一级缓存失效
     */
    @Test
    public void addStudent() {
        // 自动提交事务
        SqlSession sqlSession = factory.openSession(true);
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        System.out.println(studentMapper.getStudentById(1));
        System.out.println("添加" + studentMapper.addStudent(buildStudent()) + "个学生");
        System.out.println(studentMapper.getStudentById(1));

        sqlSession.close();
    }

    /**
     * 开启两个SqlSession，在sqlSession1中查询数据，使一级缓存生效，在sqlSession2中更新数据库
     * 结果：一级缓存只在数据库会话内部共享。
     */
    @Test
    public void testLocalCacheScope() {
        SqlSession sqlSession1 = factory.openSession(true);
        SqlSession sqlSession2 = factory.openSession(true);

        StudentMapper studentMapper1 = sqlSession1.getMapper(StudentMapper.class);
        StudentMapper studentMapper2 = sqlSession2.getMapper(StudentMapper.class);

        System.out.println("studentMapper1读取数据：" + studentMapper1.getStudentById(1));
        System.out.println("studentMapper1读取数据：" + studentMapper1.getStudentById(1));
        System.out.println("studentMapper2更新数据：" + studentMapper2.updateStudentName("小存",1));
        // 读取的脏数据
        System.out.println("studentMapper1读取数据：" + studentMapper1.getStudentById(1));
        System.out.println("studentMapper2读取数据：" + studentMapper2.getStudentById(1));
    }

    private Student buildStudent() {
        Student student = new Student();
        student.setName("缓缓");
        student.setAge(20);
        return student;
    }

    /**
     * 不提交事务，二级缓存不生效
     */
    @Test
    public void testCacheWithoutCommitOrClose() {
        SqlSession sqlSession1 = factory.openSession(true);
        SqlSession sqlSession2 = factory.openSession(true);

        StudentMapper studentMapper1 = sqlSession1.getMapper(StudentMapper.class);
        StudentMapper studentMapper2 = sqlSession2.getMapper(StudentMapper.class);

        System.out.println("studentMapper1读取数据：" + studentMapper1.getStudentById(1));
        System.out.println("studentMapper2读取数据：" + studentMapper2.getStudentById(1));
    }

    /**
     * 提交事务使用了缓存，缓存的命中率是0.5。
     * @throws Exception
     */
    @Test
    public void testCacheWithCommitOrClose() throws Exception {
        SqlSession sqlSession1 = factory.openSession(true);
        SqlSession sqlSession2 = factory.openSession(true);

        StudentMapper studentMapper = sqlSession1.getMapper(StudentMapper.class);
        StudentMapper studentMapper2 = sqlSession2.getMapper(StudentMapper.class);

        System.out.println("studentMapper读取数据: " + studentMapper.getStudentById(1));
        //sqlSession1.commit();
        sqlSession1.close();
        System.out.println("studentMapper2读取数据: " + studentMapper2.getStudentById(1));
    }

    /**
     * 更新数据，且提交事务后，相同查询走数据库
     * @throws Exception
     */
    @Test
    public void testCacheWithUpdate() throws Exception {
        SqlSession sqlSession1 = factory.openSession(true);
        SqlSession sqlSession2 = factory.openSession(true);
        SqlSession sqlSession3 = factory.openSession(true);

        StudentMapper studentMapper = sqlSession1.getMapper(StudentMapper.class);
        StudentMapper studentMapper2 = sqlSession2.getMapper(StudentMapper.class);
        StudentMapper studentMapper3 = sqlSession3.getMapper(StudentMapper.class);

        System.out.println("studentMapper读取数据: " + studentMapper.getStudentById(1));
        sqlSession1.commit();
        System.out.println("studentMapper2读取数据: " + studentMapper2.getStudentById(1));

        studentMapper3.updateStudentName("方方",1);
        sqlSession3.commit();
        System.out.println("studentMapper2读取数据: " + studentMapper2.getStudentById(1));
    }

    /**
     * MyBatis的二级缓存不适应用于映射文件中存在多表查询的情况，二级缓存基于namespace
     * @throws Exception
     */
    @Test
    public void testCacheWithDiffererntNamespace() throws Exception {
        SqlSession sqlSession1 = factory.openSession(true);
        SqlSession sqlSession2 = factory.openSession(true);
        SqlSession sqlSession3 = factory.openSession(true);

        StudentMapper studentMapper = sqlSession1.getMapper(StudentMapper.class);
        StudentMapper studentMapper2 = sqlSession2.getMapper(StudentMapper.class);
        ClassMapper classMapper = sqlSession3.getMapper(ClassMapper.class);

        System.out.println("studentMapper读取数据: " + studentMapper.getStudentByIdWithClassInfo(1));
        sqlSession1.close();

        System.out.println("studentMapper2读取数据: " + studentMapper2.getStudentByIdWithClassInfo(1));

        // updateClassName不属于studentMapper的namespace，感知不到cache变化，导致读取脏数据
        classMapper.updateClassName("特色一班",1);
        sqlSession3.commit();

        System.out.println("studentMapper2读取数据: " + studentMapper2.getStudentByIdWithClassInfo(1));
    }

    /**
     * cache ref，ClassMapper引用StudentMapper命名空间，这样两个映射文件对应的SQL操作都使用的是同一块缓存
     * @throws Exception
     */
    @Test
    public void testCacheWithDiffererntNamespaceWithCacheRef() throws Exception {
        SqlSession sqlSession1 = factory.openSession(true);
        SqlSession sqlSession2 = factory.openSession(true);
        SqlSession sqlSession3 = factory.openSession(true);

        StudentMapper studentMapper = sqlSession1.getMapper(StudentMapper.class);
        StudentMapper studentMapper2 = sqlSession2.getMapper(StudentMapper.class);
        ClassMapper classMapper = sqlSession3.getMapper(ClassMapper.class);

        System.out.println("studentMapper读取数据: " + studentMapper.getStudentByIdWithClassInfo(1));
        sqlSession1.close();

        System.out.println("studentMapper2读取数据: " + studentMapper2.getStudentByIdWithClassInfo(1));

        classMapper.updateClassName("特色一班",1);
        sqlSession3.commit();

        System.out.println("studentMapper2读取数据: " + studentMapper2.getStudentByIdWithClassInfo(1));
    }
}
