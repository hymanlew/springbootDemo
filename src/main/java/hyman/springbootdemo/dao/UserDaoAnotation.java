package hyman.springbootdemo.dao;

import hyman.springbootdemo.entity.User;
import hyman.springbootdemo.util.MybatisResults;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 在启动类中添加对mapper包扫描@MapperScan，或者直接在Mapper类上面添加注解@Mapper，建议使用第一种，不然每个mapper加个注解也挺
 * 麻烦的。
 *
 * MyBatis-3 主要提供了以下CRUD的高级注解：
 * @SelectProvider，@InsertProvider，@UpdateProvider，@DeleteProvider。这些高级注解主要用于动态SQL。
 *
 * 以 @SelectProvider 为例，主要包含两个注解属性，其中type表示工具类，method 表示工具类的某个方法，用于返回具体的SQL。
 *
 */

// @Mapper
@Repository("userDaoAnotaion")
public interface UserDaoAnotation {

    @Select("select * from user where name like #{name}")
    List<User> findByName(@Param("name") String name);

    // 如果是只有一个参数，则可以不加 @Param注解。
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

    @SelectProvider(type = MybatisResults.class,method = "userList")
    List<User> findAll2();

    /**
     * 无论什么方式,如果涉及多个参数,则必须加上 @Param注解,否则无法使用 EL表达式获取参数。
     */
    @Select("select * from user where username like #{username} and password like #{password}")
    User get(@Param("username") String username, @Param("password") String password);

    @SelectProvider(type = MybatisResults.class, method = "getBadUser")
    User getBadUser(@Param("username") String username, @Param("password") String password);

}
