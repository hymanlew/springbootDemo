package hyman.springbootdemo.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * 过滤器
 * 
 * @author RenShuaipeng
 *
 */
@WebFilter(urlPatterns = "/*") // 拦截所有的
public class LoginFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		System.out.println(httpReq.getRequestURI());
		String reqUrl = httpReq.getRequestURI();
		// 排除一些URL(静态文件...)
		if (!reqUrl.startsWith("/login")) {
			String token = request.getParameter("toten");
			if (token == null || "".equals(token)) {
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print("请先登录");
				return;
			}
		}
		// 继续下一个过滤器,或者往下执行
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
