package hyman.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("html")
public class NewController {

    @RequestMapping("index")
    public String test(HttpServletRequest request){
        request.setAttribute("test","hello man!我在 html 包里！");
        // return模板文件的名称，对应 src/main/resources/templates/html/index.html
        return "html/index";
    }
}
