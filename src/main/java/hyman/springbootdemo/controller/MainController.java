package hyman.springbootdemo.controller;

import hyman.springbootdemo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

/**
 * CrossOrigin，处理跨域请求，可加在类上（代表全部的方法），也可加在方法上（单独跨域）。
 *
 * 使用这种方式，ajax 请求时就不用声明为 jsonp 了，服务器会自动响应 jsonp。
 * $.ajax({
     type : "get",
     url: 'http://sso.isy.cn/login.json',
     data: $("#loginForm").serialize(),
     xhrFields: {
        withCredentials: true //注意这里必须指定，否则cookie无法传递过去
     },
     success : function(data){

     },
     error:function(data){
         console.log("登录出错");
         $.we.utils.gotoUrl("/");
     }
  });

 */
@CrossOrigin
@Controller
@RequestMapping
public class MainController {

    @CrossOrigin
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
