package hyman.springbootdemo.service;

import hyman.springbootdemo.dao.UserDao;
import hyman.springbootdemo.dao.UserDaoAnotation;
import hyman.springbootdemo.entity.PropSet;
import hyman.springbootdemo.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Resource(name = "userDaoAnotaion")
    private UserDaoAnotation userDaoAnotation;

    //@Override
    //public User getById(Integer id) {
    //    return userDaoAnotation.getOne(id);
    //}
    //
    //@Override
    //public List<User> findAll() {
    //    List<User> list = userDaoAnotation.findAll();
    //    return list;
    //}
    //
    //@Override
    //public List<User> findByName(String name) {
    //    return null;
    //}
    //
    //@Override
    //public List<Object> findByLimit(Integer count, Integer size) {
    //    return null;
    //}


    @Resource
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User getById(Integer id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public List<User> selectAndCount(Integer count, Integer size) {
        PropSet propSet = new PropSet();
        propSet.setObj1(count);
        propSet.setObj2(size);
        List<User> list = userDao.selectAndCount(propSet);
        return list;
    }
}
