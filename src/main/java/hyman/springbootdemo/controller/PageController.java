package hyman.springbootdemo.controller;

import hyman.springbootdemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("html")
public class PageController {

    @Resource
    public UserService userService;

    @RequestMapping("error")
    public String test(HttpServletRequest request){
        request.setAttribute("url","html/error");
        request.setAttribute("msg","测试链接！");
        return "errorSelf";
    }

    @GetMapping("addEmp")
    public String addEmp(HttpServletRequest request){
        return "html/empadd";
    }

}
