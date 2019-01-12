//package hyman.springbootdemo.dao.imp;
//
//import hyman.springbootdemo.dao.UserDao;
//import hyman.springbootdemo.entity.User;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
///**
// * mybatis 自动配置了 sqlsessionfactory，并且它只对应于接口文件（接口上声明持久层注解），接口不能有实现类，就算有也不会执行。
// * 并且实现类不能加持久层注解，否则系统无法启动
// */
//@Repository("userDao")
//public class UserDaoImp extends DemoDaoImp<User> implements UserDao{
//
//    @Override
//    public List<User> findByName(String name) {
//        return sqlSessionTemplate.selectOne("UserMapper.findByName",name);
//    }
//}
