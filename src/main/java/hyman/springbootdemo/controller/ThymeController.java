package hyman.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("do")
public class ThymeController {

    @RequestMapping("test")
    public String test(HttpServletRequest request){
        request.setAttribute("test","hello man");
        // return模板文件的名称，对应 src/main/resources/templates/index.html
        return "index";
    }



}
