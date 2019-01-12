package hyman.springbootdemo.dao;

import hyman.springbootdemo.entity.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * springboot mybatis 中 使用 xml 映射文件执行 sql 时，只需提供一个接口即可。使用 @MapperScan 或者 @Mapper 将其扫描到容器中。
 *
 */
@Repository("userDao")
public interface UserDao extends DemoDao<User>{

    //public List<User> findAll() {
    //    List<User> list = new ArrayList<>();
    //    list.add(new User(1,"E-AA",1,25,"aa@163.com",new Date()));
    //    list.add(new User(2, "E-BB", 1,30,"bb@163.com",   new Date()));
    //    list.add((new User(3, "E-CC", 0, 26,"cc@163.com",  new Date())));
    //    list.add((new User(4,"E-DD",  0, 28, "dd@163.com", new Date())));
    //    list.add((new User(5,"E-EE", 1,29, "ee@163.com",  new Date())));
    //    return list;
    //}
    //
    //public User getById(Integer id) {
    //return new User(1,"E-AA",1,25,"aa@163.com",new Date());
    //}

    List<User> findByName(String name);
}
