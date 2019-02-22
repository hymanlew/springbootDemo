package hyman.springbootdemo.util;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

public class MybatisResults {

    // 快速生成，结果集的映射关系
    public static String getResultsStr(Class origin) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@Results({\n");

        for (Field field : origin.getDeclaredFields()) {
            String property = field.getName();

            // 映射关系：对象属性(驼峰) -> 数据库字段(下划线)
            String column = new PropertyNamingStrategy.SnakeCaseStrategy().translate(field.getName()).toUpperCase();
            stringBuilder.append(String.format("@Result(property = \"%s\", column = \"%s\"),\n", property, column));
        }
        stringBuilder.append("})");
        return stringBuilder.toString();
    }

    /**
     * @Results({
         @Result(property = "userId", column = "USER_ID"),
         @Result(property = "username", column = "USERNAME"),
         @Result(property = "password", column = "PASSWORD"),
         @Result(property = "mobileNum", column = "PHONE_NUM")
         })
     */

    public String userList() {
        return "select * from user";
    }

    public String getBadUser(@Param("username") String username, @Param("password") String password){
        return new SQL(){
            {
                SELECT("*");
                FROM("user");
                if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)){
                    WHERE("username like #{username} and password like #{password}");
                }else {
                    WHERE("1=2");
                }
            }
        }.toString();
    }
}
