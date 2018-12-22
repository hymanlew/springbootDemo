package hyman.springbootdemo.controller;

import hyman.springbootdemo.entity.User;
import hyman.springbootdemo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/findByAge")
    //@PostMapping("/findByAge")
    public List<User> findByAge(Integer age) {
        return userService.findByAge(age);
    }
}
