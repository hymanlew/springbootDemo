package hyman.springbootdemo.server;

import hyman.springbootdemo.util.Logutil;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * SpringBoot默认使用Tomcat作为嵌入式的Servlet容器：
 *
 * 1，修改内置 server有关的配置，可以查看系统配置类 ServerProperties（也是EmbeddedServletContainerCustomizer），即：server.xxx
 *  （通用的 Servlet容器设置），server.tomcat.xxx（Tomcat的设置），servlet.context-path='/'（当前项目路径设置），等等。
 *
 * 2，编写一个EmbeddedServletContainerCustomizer：嵌入式的Servlet容器的定制器，来修改Servlet容器的配置。一定要将这个定制器加入到
 *   容器中（这是老版本的方法，现在不可用）。
 *
 * 3，由于 SpringBoot默认是以 jar包的方式启动嵌入式的 Servlet容器来启动 SpringBoot 的 web 应用，没有 web.xml文件。所以注册三大组
 *   件时是用：ServletRegistrationBean，FilterRegistrationBean，ServletListenerRegistrationBean。
 *
 * 4，SpringBoot自动配置 SpringMVC时，会自动注册 MVC的前端控制器 DIspatcherServlet，即 DispatcherServletAutoConfiguration。它默
 *    认拦截 / 所有请求包括静态资源，但是不拦截jsp请求（/*会拦截jsp）。可以通过 server.servletPath 来修改默认拦截的请求路径。
 *
 * 5，springboot 还支持 Jetty（适合长连接，即实时聊天场景，），Undertow（不支持 JSP，但并发性好）两种 servlet 容器（见 pom 文件）。
 */
@Configuration
public class SeverConfiguration {

    @Bean
    public ServletRegistrationBean addServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new
                MyServlet(),"/myServlet");
        // 设置启动加载顺序
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    public class MyServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            doPost(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Logutil.logger.info("============= 自定义 servlet");
            super.doPost(req, resp);
        }
    }



    /**
     * 我们常常在项目中会使用 filters 用于录调用日志、排除有XSS威胁的字符、执行权限验证等等。
     * Spring Boot自动添加了 OrderedCharacterEncodingFilter 和 HiddenHttpMethodFilter，并且我们可以自定义Filter。
     *
     * 两个步骤：
     * 1，实现Filter接口，实现Filter方法
     * 2，添加@Configuration 注解，将自定义Filter加入过滤链
     *
     * Spring Boot、Spring Web和Spring MVC等其他框架，都提供了很多servlet 过滤器可使用，我们需要在配置文件中定义这些过滤器
     * 为bean对象。
     * Tomcat 8 提供了对应的过滤器：RemoteIpFilter。通过将 RemoteFilter 这个过滤器加入过滤器调用链即可使用它。
     */
    @Bean
    public RemoteIpFilter remoteIpFilter(){
        return new RemoteIpFilter();
    }

    @Bean
    public FilterRegistrationBean testFilterRegist(){
        FilterRegistrationBean regist = new FilterRegistrationBean();
        //注入过滤器
        regist.setFilter(new MyFilter());
        //拦截规则
        regist.addUrlPatterns("/*");
        //过滤器初始化参数
        regist.addInitParameter("name","value");
        //过滤器名称
        regist.setName("myfilter");
        //为 filter 设置排序值，让 spring 在注册 filter之前排序后再依次注册。过滤器顺序
        regist.setOrder(1);
        return regist;
    }

    public class MyFilter implements Filter {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest)servletRequest;
            System.out.println("============= 拦截 url："+request.getRequestURI());
            filterChain.doFilter(servletRequest,servletResponse);
        }

        @Override
        public void destroy() {
        }
    }



    @Bean
    public ServletListenerRegistrationBean addListener(){
        ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean(new MyListener());
        return registrationBean;
    }

    public class MyListener implements ServletContextListener{
        @Override
        public void contextInitialized(ServletContextEvent servletContextEvent) {
            Logutil.logger.info("============= 自定义 listener ==== 服务器启动");
        }

        @Override
        public void contextDestroyed(ServletContextEvent servletContextEvent) {
            Logutil.logger.info("============= 自定义 listener ==== 服务器关闭");
        }
    }
}
