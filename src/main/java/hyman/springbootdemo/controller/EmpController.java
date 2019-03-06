package hyman.springbootdemo.controller;

import hyman.springbootdemo.entity.User;
import hyman.springbootdemo.service.UserService;
import hyman.springbootdemo.util.Logutil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @PostMapping("/findByName")
    @ResponseBody
    public List<User> findByName(String name) {
        List<User> list = userService.findByName(name);
        return list;
    }

    @GetMapping("/emps")
    public String emp(Map<String,Object> map){
        map.put("emps",userService.findAll());
        return "html/emplist";
    }

    /**
     * 需要注意在方法中使用验证时，@Valid 的参数后必须紧挨着一个BindingResult 参数，否则spring会在校验不通过时直接抛出异常。
     */
    @PostMapping("/addEmp")
    public String addEmp(Map<String,Object> map, @Validated User user, BindingResult result){
        Logutil.logger.info("=== 添加工人："+user.toString());

        if(result.hasErrors()){
            Logutil.getValidData(map, result);
            return "html/empadd";
        }else {
            // 在这里必须使用 redirect 重定向，因为两个方法的请求方式不同（GET，POST）。如果是同一种请求方式，则应该使用 forward。
            //return "forward:/emp/emps";
            return "redirect:/emp/emps";
        }
    }

    @GetMapping("/getById/{id}")
    public String getById(@PathVariable Integer id, Map<String,Object> map) {
        Logutil.logger.info("=== 用 ID 查询工人："+id);

        map.put("emp", userService.getById(id));
        return "html/empadd";
    }

    @PutMapping("/addEmp")
    public String updateEmp(Map<String,Object> map, @Validated User user, BindingResult result){
        Logutil.logger.info("=== 修改工人信息："+user.toString());

        if(result.hasErrors()){
            Logutil.getValidData(map, result);
            return "html/empadd";
        }else {
            //userService.update(user);

            // 在这里必须使用 redirect 重定向，因为两个方法的请求方式不同（GET，POST）。如果是同一种请求方式，则应该使用 forward。
            //return "forward:/emp/emps";
            return "redirect:/emp/emps";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id){
        Logutil.logger.info("=== 用 ID 删除工人："+id);

        userService.delete(id);
        return "redirect:/emp/emps";
    }

    @GetMapping("/findByLimit/{count}/{size}")
    public String findByLimit(Map<String,Object> map,@PathVariable Integer count,@PathVariable Integer size){
        List<User> list = userService.selectAndCount(count,size);
        map.put("emps",list.get(0));
        map.put("count",list.get(1));
        Logutil.logger.info("=== 统计工人："+list.get(1));

            return "html/emplist";
    }

}
