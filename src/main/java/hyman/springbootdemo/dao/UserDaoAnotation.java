package hyman.springbootdemo.dao;

import hyman.springbootdemo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

// 在启动类中添加对mapper包扫描@MapperScan，或者直接在Mapper类上面添加注解@Mapper,建议使用上面那种，不然每个mapper加个注解也挺麻烦的
// @Mapper
@Repository("userDaoAnotaion")
public interface UserDaoAnotation {

    @Select("select * from user where name like #{name}")
    User findByName(@Param("name") String name);

    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(property = "password",  column = "password"),
            @Result(property = "name", column = "name")
    })
    User getOne(Integer id);

    @Insert("INSERT INTO user(name,password,age) VALUES(#{name}, #{password}, #{age})")
    void insert(User user);

    @Update("UPDATE user SET name=#{name},password=#{password} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Integer id);

    @Results(id = "userResult",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "name",column = "name")
            //.....
    })
    @Select("select * from user where age=#{age}")
    List<User> findByAge(@Param("age") Integer age);
}