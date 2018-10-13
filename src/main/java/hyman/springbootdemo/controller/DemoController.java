package hyman.springbootdemo.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
<<<<<<< HEAD
import hyman.springbootdemo.entity.Message;
import hyman.springbootdemo.entity.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
=======
import hyman.springbootdemo.entity.User;
import org.springframework.web.bind.annotation.*;

>>>>>>> fc86736a21522464b4b9832f277ec1070351a2f7
import java.util.*;

/**
 * Spring Boot构建RESTful：
 * 即在设计web接口的时候，REST主要是用于定义接口名，接口名一般是用名词写，不用动词，而表达“获取，删除，更新”的操作，就用请求
 * 类型来区分。
 * REST架构风格包含了统一接口的概念，统一接口包含一组受限的良定义的操作，由它们进行资源的访问和操作。即不论什么资源，都使用相
 * 同的接口。
 * 而这种风格接口的好处是：前后端分离。前端拿到数据只负责展示和渲染，不对数据做任何处理。后端处理数据并以JSON格式传输出去，定
 * 义这样一套统一的接口，在web，ios，android三端都可以用相同的接口。
 *
 * GET：···向特定的资源发出请求。
 * POST：··向指定的服务器资源提交相应的数据的一种处理请求，比如说提交表单、上传文件。数据被包含在请求实体中。
 * PUT：···使用从客户端向服务器传送的数据取代指定的文档的内容。
 * DELETE：请求服务器删除指定的页面
 * OPTIONS：允许客户端查看服务器的性能。
 * HEAD：   类似于get请求，只不过返回的响应中没有具体的内容，只用于获取报头
 * TRANS：  回显服务器收到的请求，主要用于测试或诊断。
 * CONNECT：HTTP/1.1 协议中预留给能够将连接改为管道方式的代理服务器。
 *
 * 1，@Controller：修饰class，用来创建处理http请求的对象，可以响应 html。
 *
 * 2，@RestController：Spring4之后加入的注解，原本在 @Controller中返回 json需要 @ResponseBody来配合。而如果直接使用
 *    @RestController替代 @Controller 则就不需要再配置 @ResponseBody，默认返回json格式，且不解析 html，即不返回 html。
 *
 */

// 该注解配合 jackson，在进行类的序列化时忽略值为 null 的属性
@JsonInclude(JsonInclude.Include.NON_NULL)
@RestController
@RequestMapping("/demo")
public class DemoController {

<<<<<<< HEAD
    @Resource
    private Message message;

=======
>>>>>>> fc86736a21522464b4b9832f277ec1070351a2f7
    // 创建线程安全的Map
    public static Map<Integer,User> userMap = Collections.synchronizedMap(new HashMap<>());

    @RequestMapping("/index")
    public String index(){
<<<<<<< HEAD
        return "hello world you，"+message.getTitle()+"，"+message.getDescription();
=======
        return "hello world you";
>>>>>>> fc86736a21522464b4b9832f277ec1070351a2f7
    }

    // 获取用户列表
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<User> getUserList() {

        // 还可以通过 @RequestParam 接收从页面传递来的参数，来进行查询条件或者翻页信息的传递
        List<User> r = new ArrayList<User>(userMap.values());
        return r;
    }

    // 创建User
    @RequestMapping(value="/user", method=RequestMethod.POST)
    public String postUser(@ModelAttribute User user) {

        // 除了 @ModelAttribute 绑定参数之外，还可以通过 @RequestParam从页面中接收传递的参数
        userMap.put(user.getId(), user);
        return "success";
    }

    // 根据 id，获取 User信息，variable：变量
    @RequestMapping(value = "/user/{id}",method = RequestMethod.PUT)
    public User getUser(@PathVariable Integer id) {

        // url中的 id可通过 @PathVariable 绑定到函数的参数中
        return userMap.get(id);
    }

    // 根源 {id}"的 DELETE请求，用来删除User
    @RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {

        userMap.remove(id);
        return "success";
    }

<<<<<<< HEAD
    // 我们使用 @Validated（验证，确认），来实现对传入的参数的验证，而不需要再写一堆的 if-else。
    @RequestMapping("/saveUser")
    public void saveUser(@Validated User user, BindingResult bindingResult){

        System.out.println("user:"+user);
        if(bindingResult.hasErrors()){
            // spring 框架中的类
            List<ObjectError> list = bindingResult.getAllErrors();
            for(ObjectError error:list){
                System.out.println(error.getCode()+"--"+error.getDefaultMessage());
            }
        }
    }

    /**
     * @RequestParam 适用于接收单个参数变量。
     * @PathVariable 适用于接收单个参数变量，并且可以把变量值传递给访问路径中。
     *               并且路径传参，与 http讲求方法是不相关的，即使用 get、post 方法也能传参
     * @ModelAttribute 适用于接收一组参数变量。
     */

=======
    /**
     * @RequestParam 适用于接收单个参数变量。
     * @PathVariable 适用于接收单个参数变量，并且可以把变量值传递给访问路径中。
     * @ModelAttribute 适用于接收一组参数变量。
     */
>>>>>>> fc86736a21522464b4b9832f277ec1070351a2f7
}
