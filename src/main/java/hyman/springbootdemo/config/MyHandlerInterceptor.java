package hyman.springbootdemo.config;

import hyman.springbootdemo.util.Logutil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter（过滤器）：
 * 该接口定义在 javax.servlet 包中，配置在 web.xml 中，只在 Servlet 前后起作用。它是作用于客户端及服务端的请求和响应（request/response）。
 * Filter 通常不考虑servlet 的实现，它是在 Servlet 规范中定义的，是 Servlet 容器支持的。不能够使用 Spring 容器资源，是被 Server(like
 * Tomcat) 调用的。
 *
 * 如果需要调用 spring 容器内的资源，则必须从容器的上下文中获取：
 * WebApplicationContext ac = (WebApplicationContext )filterConfig.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
 * roleService = ac.getBean(RoleService.class);
 *
 *
 * Interceptor（拦截器）：
 * 接口 HandlerInterceptor 定义在org.springframework.web.servlet 包中，拦截器能够深入到方法前后、异常抛出前后等，因此拦截器的使用具有更大的
 * 弹性。它允许用户介入请求的生命周期，在请求过程中获取信息，Interceptor 通常和请求更加耦合。
 * 它既可以用于 Web 程序，也可以用于 Application、Swing 程序中。是在 Spring容器内的，是Spring框架支持的。是一个Spring的组件，归Spring管理，
 * 配置在Spring文件中，因此能使用 Spring 里的任何资源、对象，例如 Service对象、数据源、事务管理等，通过IoC注入到拦截器即可。是被 Spring 调用的。
 *
 *
 * 因此在 Spring 构架的程序中，要优先使用拦截器。几乎所有 Filter 能够做的事情，interceptor 都能够轻松的实现。但它们使用范围不同，规范不同。
 * 拦截器（Interceptor）和过滤器（Filter）的执行顺序：过滤前（filter）-- 拦截前（interceptor）-- Action处理-拦截后-过滤后。
 *
 * 自定义 AOP 拦截器：
 */
public class MyHandlerInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 通过所有的客户端请求
        String uri = request.getRequestURI();
        boolean client = false;
        if(uri.contains("client") || uri.contains("error")){
            client = true;
            return true;
        }
        if(client){
            return true;
        }
        // 因为如果没有登录时，security 框架就会自动拦截，是走的内部拦截器。所以这里只需要获取当前登录成功的用户名，也不用判断是否为空。
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = ((org.springframework.security.core.userdetails.User)user).getUsername();

        if("user".equals(name) || "admin".equals(name)){
            Logutil.logger.info("============ 当前用户："+name+" =============");
            request.getSession().setAttribute("user",name);
            return true;
        }
        Logutil.logger.warn("============ 当前为未登录状态！=============");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
