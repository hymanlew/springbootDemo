package hyman.springbootdemo.config;

import hyman.springbootdemo.util.ServerException;
import org.apache.ibatis.javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring Boot提供了一个默认的映射：/error，当处理中抛出异常之后，会转到该请求中处理，并且该请求有一个全局的错误页面用
 * 来展示异常内容。默认 SpringBoot 就会去找到一个页面 error/状态码：
 * 模板引擎可用的情况下会返回到 errorViewName 指定的视图地址，即引擎文件夹下的 error/状态码 页面。
 * 模板引擎不可用时，就在静态资源文件夹下找 errorViewName 对应的页面 error/404.html。
 *
 * 在有精准匹配的页面时，就会返回与状态码对应的页面（如 404 对应 404 名称的页面）。如果没有对应的页面时就会根据状态码
 * 找公共的页面（如 4 开头的状态码找 4xx 页面，5 开头的状态码找 5xx 页面）。当然这些是在没有自定义统一异常处理器时的流程。
 *
 * 在实际应用中，系统的错误页面对用户来说并不够友好，我们通常需要去实现我们自己的异常提示。所以 springboot 已经实现好了，直接
 * 放置好页面就好，一般不需要自定义异常处理器。
 *
 * @ControllerAdvice 定义全局统一的异常处理类，而不是在每个Controller中逐个定义。也可以使用 basePackages 指定作用的包。
 * @ExceptionHandler 用来定义函数针对的异常类型（指定异常类型），最后将Exception对象和请求URL映射到error.html中。
 *
 * 利用 @ControllerAdvice + @ExceptionHandler 组合处理Controller层RuntimeException异常
 * 当前的设置有问题，因为无论是系统错误，还是服务器错误都会跳转页面，以后解决。
 *
 * 另外如果有必要的话，自定义异常打印日志一定要和其他的代码错误分离，不应该混为一谈。其次自定义异常类也并不一定要记录日志，我们
 * 应该提供独立的log对象（另外声明一个全局异常的日志记录），方便开关。
 *
 *
 * exception.getLocalizedMessage()， 方法返回 Exception的本地化描述。
 * exception.getMessage()，          方法返回 Exception的详细消息字符串。
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "/errorSelf";
    public static ModelAndView mav = new ModelAndView();
    public static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    static {
        mav.setViewName(DEFAULT_ERROR_VIEW);
    }

    // 通用捕获异常的业务
    //@ExceptionHandler(Exception.class)
    //public ModelAndView noFoundHandler2(HttpServletRequest request,Exception e){
    //
    //    mav.setAttribute("msg",""+e.getMessage());
    //    mav.setAttribute("url",""+request.getRequestURL());
    //    mav.setAttribute("test","测试页面");
    //    logger.error("======================================"+e.getMessage());
    //    return mav;
    //}

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView defaultHandler(HttpServletRequest request,Exception e){

        /**
         * ModelAndView 类用来存储处理完后的结果数据，以及显示该数据的视图。从名字上看 Model代表模型，View代表视图，这个
         * 名字就很好地解释了该类的作用。
         *
         * 业务处理器调用模型层处理完用户请求后，把结果数据存储在该类的model属性中，把要返回的视图信息存储在该类的view属性中，
         * 然后让该 ModelAndView返回该 Spring MVC框架。框架通过调用配置文件中定义的视图解析器，对该对象进行解析，最后把结果
         * 数据显示在指定的页面上。
         *
         * ModelAndView构造方法可以指定返回的页面名称，也可以通过setViewName()方法跳转到指定的页面。
         * 使用addObject()设置需要返回的值，addObject()有几个不同参数的方法，可以默认和指定返回对象的名字。
         *
         * ModelAndView对象有两个作用：
         * 作用一，设置转向地址（这也是ModelAndView和ModelMap的主要区别），ModelAndView view = new ModelAndView("path:url");
         * 作用二, 用于传递控制方法处理结果数据到结果页面，也就是说我们把需要在结果页面上需要的数据放到 ModelAndView对象中即可，
         *         他的作用类似于 request对象的setAttribute方法的作用，用来在一个请求过程中传递处理的数据。
         *         即：addObject(String key,Object value);
         */
        mav.addObject("exception",e.getMessage());
        mav.addObject("url",request.getRequestURL());
        logger.error("======================================"+e.getMessage());
        return mav;

        // 不可以直接返回 string 页面，会被重定向。
        //request.setAttribute("msg",""+e.getMessage());
        ////request.setAttribute("exception",e);
        //request.setAttribute("url",""+request.getRequestURL());
        //logger.error("======================================"+e.getMessage());
        //return DEFAULT_ERROR_VIEW;
    }

    /*
     *  400-Bad Request，响应 json 格式数据（浏览器，客户端都会返回 json）
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Map<String,Object> handleHttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException e) {
        Map<String,Object> map = new HashMap<>();
        map.put("exception",e.getMessage());
        map.put("url",""+request.getRequestURL());
        logger.error("======================================"+e.getMessage());
        return map;
    }

    /**
     * 转发到系统默认路径 /error 进行自适应响应效果处理（页面或者 json数据）。但是以下代码返回的页面或 json数据还是系统默认的。所以如果要将我
     * 们的定制数据携带出去就需要自定义一些组件：
     *
     * 1，在出现错误以后，系统会来到 /error 请求，会被BasicErrorController处理，响应出去可以获取的数据是由 getErrorAttributes 得到的（它是
     *    AbstractErrorController（ErrorController）规定的方法）。所以可以自定义一个 ErrorController 的实现类（或者是 AbstractErrorController
     *    的子类），放在容器中。但是这种方式太麻烦。
     *
     * 2，页面上，或者是 json返回能用的数据都是通过 errorAttributes.getErrorAttributes 得到的，是容器中 DefaultErrorAttributes.getErrorAttributes()
     *    进行数据处理的。所以可以自定义一个 DefaultErrorAttributes 的实现类。
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationException(HttpServletRequest request,MethodArgumentNotValidException e) {

        //传入我们自己的错误状态码 4xx 5xx，否则就不会进入定制错误页面的解析流程
        request.setAttribute("javax.servlet.error.status_code",500);

        Map<String,Object> map = new HashMap<>();
        map.put("exception",e.getMessage());
        map.put("url",""+request.getRequestURL());
        logger.error("======================================"+e.getMessage());

        //转发到/error
        return "forward:/error";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView noFoundHandler(HttpServletRequest request,Exception e){

        mav.addObject("exception",e.getMessage());
        mav.addObject("url",""+request.getRequestURL());
        logger.error("======================================"+e.getMessage());
        return mav;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView noFoundHandler1(HttpServletRequest request,Exception e){

        mav.addObject("exception",e.getMessage());
        mav.addObject("url",""+request.getRequestURL());
        logger.error("======================================"+e.getMessage());
        return mav;
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServerException.class)
    public ModelAndView customException(HttpServletRequest request,ServerException e){

        mav.addObject("exception",e.getLocalizedMessage());
        mav.addObject("url",request.getRequestURL());
        logger.error("======================================"+e.getMessage());
        return mav;
    }
}
