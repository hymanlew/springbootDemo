package hyman.springbootdemo.service;

import hyman.springbootdemo.entity.User;

import java.util.List;

public interface UserService {
    List<User> findByAge(Integer age);
}
