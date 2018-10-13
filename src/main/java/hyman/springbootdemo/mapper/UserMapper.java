package hyman.springbootdemo.mapper;

import hyman.springbootdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from ")
    User findByName(@Param("name") String name);
}
