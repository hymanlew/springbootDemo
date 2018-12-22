package hyman.springbootdemo.dao;

import hyman.springbootdemo.entity.User;
import org.apache.ibatis.annotations.*;

// 在启动类中添加对mapper包扫描@MapperScan，或者直接在Mapper类上面添加注解@Mapper,建议使用上面那种，不然每个mapper加个注解也挺麻烦的
// @Mapper
public interface UserDaoAno {

    @Select("select * from user where name like #{name}")
    User findByName(@Param("name") String name);

    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(property = "userSex",  column = "user_sex", javaType = User.class),
            @Result(property = "nickName", column = "nick_name")
    })
    User getOne(Long id);

    @Insert("INSERT INTO user(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
    void insert(User user);

    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);
}
