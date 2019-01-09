package hyman.springbootdemo.dao;

import hyman.springbootdemo.entity.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 做为在 service中调用的 dao层，但实际工作的是其父类或是父接口
 * 这样是为了体现高可用（或者是适配器模式）
 */
@Repository("userDao")
public class UserDao extends DemoDao<User>{

    /*
     * 如果父级全是 interface接口类型，而且接口实现也是继承关系时，即 service接口及其实现，dao接口及其实现，otherdao接口
     * 及其实现全部是实现的继承关系，则在调用总接口的公共方法时，特别要注意记得，设置父类 dao接口指向当前 dao接口，否则会
     * 无法调用顶级父类的公共方法。
     * 即适配器模式。
     */

    @Resource(name="sqlSessionTemplate")
    private transient SqlSessionTemplate sqlSessionTemplate;

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        list.add(new User(1,"E-AA",1,25,"aa@163.com",new Date()));
        list.add(new User(2, "E-BB", 1,30,"bb@163.com",   new Date()));
        list.add((new User(3, "E-CC", 0, 26,"cc@163.com",  new Date())));
        list.add((new User(4,"E-DD",  0, 28, "dd@163.com", new Date())));
        list.add((new User(5,"E-EE", 1,29, "ee@163.com",  new Date())));
        return list;
    }

    public User getById(Integer id) {
        return new User(1,"E-AA",1,25,"aa@163.com",new Date());
    }
}
