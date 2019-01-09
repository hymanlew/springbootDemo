package hyman.springbootdemo.controller;

import hyman.springbootdemo.entity.User;
import hyman.springbootdemo.service.UserService;
import hyman.springbootdemo.util.Logutil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * select == get
 * insert == post
 * update == put
 * delete == delete
 */
@Controller
@RequestMapping("/emp")
public class EmpController {

    @Resource
    private UserService userService;

    @GetMapping("/findByAge{age}")
    public List<User> findByAge(Integer age) {
        return userService.findByAge(age);
    }

    @GetMapping("/emps")
    public String emp(Map<String,Object> map){
        map.put("emps",userService.findAll());
        return "html/emplist";
    }

    @PostMapping("/addEmp")
    public String addEmp(Map<String,Object> map,User user){
        Logutil.logger.info("=== 添加工人："+user.toString());

        // 在这里必须使用 redirect 重定向，因为两个方法的请求方式不同（GET，POST）。如果是同一种请求方式，则应该使用 forward。
        //return "forward:/emp/emps";
        return "redirect:/emp/emps";
    }

    @GetMapping("/getById/{id}")
    public String getById(@PathVariable Integer id, Map<String,Object> map) {
        Logutil.logger.info("=== 用 ID 查询工人："+id);

        map.put("emp", userService.getById(id));
        return "html/empadd";
    }

    @PutMapping("/addEmp")
    public String updateEmp(Map<String,Object> map,User user){
        Logutil.logger.info("=== 修改工人信息："+user.toString());

        // 在这里必须使用 redirect 重定向，因为两个方法的请求方式不同（GET，POST）。如果是同一种请求方式，则应该使用 forward。
        //return "forward:/emp/emps";
        return "redirect:/emp/emps";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id){
        Logutil.logger.info("=== 用 ID 删除工人："+id);

        return "redirect:/emp/emps";
    }

}
