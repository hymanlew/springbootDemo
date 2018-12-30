package hyman.springbootdemo.service;

import hyman.springbootdemo.dao.UserDaoAnotation;
import hyman.springbootdemo.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Resource(name = "userDaoAnotaion")
    private UserDaoAnotation userDao;

    @Override
    public List<User> findByAge(Integer age) {
        return userDao.findByAge(age);
    }
}
