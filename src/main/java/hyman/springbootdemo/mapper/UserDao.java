package hyman.springbootdemo.mapper;

import hyman.springbootdemo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    @Results(id = "userResult",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "name",column = "name")
            //.....
    })
    @Select("select * from user where age=#{age}")
    List<User> findByAge(@Param("age") Integer age);
}
