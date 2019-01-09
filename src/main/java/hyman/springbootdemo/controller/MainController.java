package hyman.springbootdemo.controller;

import hyman.springbootdemo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping
public class MainController {

    @RequestMapping(value = {"/login"})
    public String gologin(){
        return "login";
    }

    @RequestMapping(value = {"/security/login"})
    public String login2(Map<String,Object> map){

        map.put("test","<h3>测试特殊字符解析！</h3>");
        map.put("user",new User(1,"hyman","123"));
        map.put("list", Arrays.asList("遍历-A","遍历-B","遍历-C","遍历-D"));
        return "index";
    }

    @RequestMapping("/security/welcome")
    public String login3(Map<String,Object> map){
        // 不需要手动重定向到 security 系统的登录页面，并且这样设置的话，登录成功后还会去登录页面，形成死循环。
        map.put("test","登录成功！");
        map.put("user",new User(1,"hyman","123"));
        return "index";
    }

    @RequestMapping("/parselogin")
    public String parselogin(Map<String,Object> map, String logout, String error){
        // 不需要手动重定向到 security 系统的登录页面，并且这样设置的话，登录成功后还会去登录页面，形成死循环。
        //return "redirect:/login";

        if(logout!=null && logout!=""){
            map.put("logout","已退出登录！");
        }
        if(error!=null && error!=""){
            map.put("error","用户名或密码错！");
        }
        return "login";
    }
}
