package hyman.springbootdemo.dao;

import hyman.springbootdemo.entity.User;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 在启动类中添加对mapper包扫描@MapperScan，或者直接在Mapper类上面添加注解@Mapper，建议使用第一种，不然每个mapper加个注解也挺
 * 麻烦的。
 *
 * 另外 boot 2.0 版本以上，它会自动将表字段与实体类的属性一一对应起来。不需要在全局配置文件中配置 map-underscore-to-camel-case: true，
 * 否则系统无法启动。
 */

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

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("INSERT INTO user(name,password,age) VALUES(#{name}, #{password}, #{age})")
    int insert(User user);

    @Update("UPDATE user SET name=#{name},password=#{password} WHERE id =#{id}")
    int update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    int delete(Integer id);

    @Results(id = "userResult",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "name",column = "name")
            //.....
    })

    @Select("select * from user")
    List<User> findAll();
}
