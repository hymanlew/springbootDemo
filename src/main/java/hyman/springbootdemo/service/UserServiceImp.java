package hyman.springbootdemo.service;

import hyman.springbootdemo.entity.User;
import hyman.springbootdemo.mapper.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> findByAge(Integer age) {
        return userDao.findByAge(age);
    }
}
