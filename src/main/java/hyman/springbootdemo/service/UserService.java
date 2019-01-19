package hyman.springbootdemo.service;

import hyman.springbootdemo.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User getById(Integer id);

    List<User> findByName(String name);

    List<User> selectAndCount(Integer count, Integer size);

    User update(User user);

    void delete(Integer id);
}
