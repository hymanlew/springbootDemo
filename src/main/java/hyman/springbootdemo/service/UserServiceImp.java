package hyman.springbootdemo.service;

import hyman.springbootdemo.dao.UserDao;
import hyman.springbootdemo.dao.UserDaoAnotation;
import hyman.springbootdemo.entity.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Resource(name = "userDaoAnotaion")
    private UserDaoAnotation userDaoAnotation;

    @Resource
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findByAge(Integer age) {
        return userDaoAnotation.findByAge(age);
    }

    @Override
    public User getById(Integer id) {
        return userDao.getById(id);
    }
}
