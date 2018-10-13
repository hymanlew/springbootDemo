package hyman.springbootdemo.filter;

import org.springframework.boot.web.filter.OrderedCharacterEncodingFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * 自定义 Filter：
 * 我们经常会在项目中使用 filter 用于记录请求日志、排除有 XSS 威胁的字符、执行权限验证等等。sping boot自动添加了 OrderedCharacter
 * EncodingFilter 和 HiddenHttpMethodFilter，并且可以自定义 Filter。
 */
@Configuration
public class MyFilter implements Filter{

    public void destroy() {
        // TODO Auto-generated method stub
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        System.out.println("自定义 filter，url:"+req.getRequestURI());

        //执行后续的 Servlet
        chain.doFilter(request, response);
    }


    public void init(FilterConfig fConfig) throws ServletException {

    }
}
