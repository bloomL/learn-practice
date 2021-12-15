package mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @Description ClassMapper
 * @Author liguo
 * @Date 2021/12/8 15:54
 **/
public interface ClassMapper {
    int updateClassName(@Param("name") String className, @Param("id") int id);
}
