package hyman.springbootdemo.dao;

import hyman.springbootdemo.entity.Person;
import hyman.springbootdemo.entity.User;
import org.apache.ibatis.annotations.*;
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
@Repository("personDaoAnotaion")
public interface PersonDaoAnotation {

    @Select("SELECT * FROM person WHERE id = #{id}")
    //@Results({
    //        @Result(property = "age",  column = "age"),
    //        @Result(property = "lastName", column = "last_name")
    //})
    Person getOne(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("INSERT INTO person(name,password,age) VALUES(#{name}, #{password}, #{age})")
    int insert(Person person);

    @Update("UPDATE person SET name=#{name},password=#{password} WHERE id =#{id}")
    int update(Person person);

    @Delete("DELETE FROM person WHERE id =#{id}")
    int delete(Integer id);

    @Results(id = "userResult",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "lastName", column = "last_name")
            //.....
    })

    @Select("select * from person")
    List<User> findAll();
}
