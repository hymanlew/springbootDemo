package hyman.springbootdemo.filter;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义Filter：
 * 我们常常在项目中会使用 filters 用于录调用日志、排除有XSS威胁的字符、执行权限验证等等。
 * Spring Boot自动添加了 OrderedCharacterEncodingFilter 和 HiddenHttpMethodFilter，并且我们可以自定义Filter。
 *
 * 两个步骤：
 * 1，实现Filter接口，实现Filter方法
 * 2，添加@Configuration 注解，将自定义Filter加入过滤链
 */

@Configuration
public class WebConfiguration {

    /**
     * Spring Boot、Spring Web和Spring MVC等其他框架，都提供了很多servlet 过滤器可使用，我们需要在配置文件中定义这些过滤器
     * 为bean对象。
     * Tomcat 8 提供了对应的过滤器：RemoteIpFilter。通过将 RemoteFilter 这个过滤器加入过滤器调用链即可使用它。
     */
    @Bean
    public RemoteIpFilter remoteIpFilter(){
        return new RemoteIpFilter();
    }

    /**
     * Spring 提供了 FilterRegistrationBean 类，此类提供 setOrder 方法，可以为 filter 设置排序值，让 spring 在注册 web
     * filter之前排序后再依次注册。可以为自定义 Filter设置执行顺序。
     */
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
        //过滤器顺序
        regist.setOrder(1);
        return regist;
    }


    public class MyFilter implements Filter{
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
}
