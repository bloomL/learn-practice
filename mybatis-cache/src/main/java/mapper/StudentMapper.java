package mapper;

import model.Student;
import org.apache.ibatis.annotations.Param;

/**
 * @Description StudentMapper
 * @Author liguo
 * @Date 2021/12/8 15:57
 **/
public interface StudentMapper {

    Student getStudentById(int id);

    int addStudent(Student student);

    int updateStudentName(@Param("name") String name, @Param("id") int id);

    Student getStudentByIdWithClassInfo(int id);
}
