package hyman.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/parselogin")
    public String login(ModelMap map,String logout,String error){
        // 不需要手动重定向到 security 系统的登录页面，并且这样设置的话，登录成功后还会去登录页面，形成死循环。
        //return "redirect:/login";

        // return模板文件的名称，对应 src/main/resources/templates/index.html
        if(logout!=null && logout!=""){
            map.addAttribute("logout","退出登录！");
            return "login";
        }
        if(error!=null && error!=""){
            map.addAttribute("error","登录失败！");
            return "login";
        }
        return "index";
    }

    @RequestMapping("/dologin")
    public String gologin(){
        return "login";
    }

    @RequestMapping(value = {"/"})
    public String parselogin(){
        return "index";
    }
}
