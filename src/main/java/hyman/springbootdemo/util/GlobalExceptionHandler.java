package hyman.springbootdemo.util;

import org.apache.ibatis.javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * Spring Boot提供了一个默认的映射：/error，当处理中抛出异常之后，会转到该请求中处理，并且该请求有一个全局的错误页面用
 * 来展示异常内容。
 * 在实际应用中，系统的错误页面对用户来说并不够友好，我们通常需要去实现我们自己的异常提示。
 *
 * @ControllerAdvice 定义全局统一的异常处理类，而不是在每个Controller中逐个定义。
 * @ExceptionHandler 用来定义函数针对的异常类型（指定异常类型），最后将Exception对象和请求URL映射到error.html中。
 *
 * 利用 @ControllerAdvice + @ExceptionHandler 组合处理Controller层RuntimeException异常
 * 当前的设置有问题，因为无论是系统错误，还是服务器错误都会跳转页面，以后解决。
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "errorSelf";
    public static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    public String noFoundHandler2(HttpServletRequest request,Exception e){

        request.setAttribute("msg",""+e.getMessage());
        //request.setAttribute("exception",e);
        request.setAttribute("url",""+request.getRequestURL());
        request.setAttribute("test","测试页面");
        logger.error("======================================"+e.getMessage());
        return DEFAULT_ERROR_VIEW;
    }

    ///**
    // * 500 - Internal Server Error
    // */
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    //@ExceptionHandler(Exception.class)
    //public ModelAndView defaultHandler(HttpServletRequest request,Exception e){
    //
    //    /**
    //     * ModelAndView 类用来存储处理完后的结果数据，以及显示该数据的视图。从名字上看 Model代表模型，View代表视图，这个
    //     * 名字就很好地解释了该类的作用。
    //     *
    //     * 业务处理器调用模型层处理完用户请求后，把结果数据存储在该类的model属性中，把要返回的视图信息存储在该类的view属性中，
    //     * 然后让该 ModelAndView返回该 Spring MVC框架。框架通过调用配置文件中定义的视图解析器，对该对象进行解析，最后把结果
    //     * 数据显示在指定的页面上。
    //     *
    //     * ModelAndView构造方法可以指定返回的页面名称，也可以通过setViewName()方法跳转到指定的页面。
    //     * 使用addObject()设置需要返回的值，addObject()有几个不同参数的方法，可以默认和指定返回对象的名字。
    //     *
    //     * ModelAndView对象有两个作用：
    //     * 作用一，设置转向地址（这也是ModelAndView和ModelMap的主要区别），ModelAndView view = new ModelAndView("path:url");
    //     * 作用二, 用于传递控制方法处理结果数据到结果页面，也就是说我们把需要在结果页面上需要的数据放到 ModelAndView对象中即可，
    //     *         他的作用类似于 request对象的setAttribute方法的作用，用来在一个请求过程中传递处理的数据。
    //     *         即：addObject(String key,Object value);
    //     */
    //
    //    ModelAndView mav = new ModelAndView();
    //    mav.addObject("exception",e.getMessage());
    //    mav.addObject("url",request.getRequestURL());
    //    mav.setViewName(DEFAULT_ERROR_VIEW);
    //    return mav;
    //}
    //
    ///*
    // *  400-Bad Request
    // */
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    //@ExceptionHandler(HttpMessageNotReadableException.class)
    //public String handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    //    logger.error("无法读取JSON...", e);
    //    return DEFAULT_ERROR_VIEW;
    //}
    //
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    //@ExceptionHandler(MethodArgumentNotValidException.class)
    //public String handleValidationException(MethodArgumentNotValidException e)
    //{
    //    logger.error("参数验证异常...", e);
    //    return DEFAULT_ERROR_VIEW;
    //}
    //
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    //@ExceptionHandler(NoHandlerFoundException.class)
    //public String noFoundHandler(HttpServletRequest request,Exception e){
    //
    //    request.setAttribute("msg",""+e.getMessage());
    //    //request.setAttribute("exception",e);
    //    request.setAttribute("url",""+request.getRequestURL());
    //    request.setAttribute("test","测试页面");
    //    logger.error("======================================"+e.getMessage());
    //    return DEFAULT_ERROR_VIEW;
    //}
    //
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    //@ExceptionHandler(NotFoundException.class)
    //public String noFoundHandler1(HttpServletRequest request,Exception e){
    //
    //    request.setAttribute("msg",""+e.getMessage());
    //    //request.setAttribute("exception",e);
    //    request.setAttribute("url",""+request.getRequestURL());
    //    request.setAttribute("test","测试页面");
    //    logger.error("======================================"+e.getMessage());
    //    return DEFAULT_ERROR_VIEW;
    //}

}
