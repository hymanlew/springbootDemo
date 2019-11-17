package hyman.springbootdemo.config;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class OnlineListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		ServletContext ctx = se.getSession().getServletContext();
		Integer totalCount = (Integer) ctx.getAttribute("totalCount");
		if (totalCount == null) {
			totalCount = 0;
		}
		totalCount++;
		System.out.println(totalCount);
		ctx.setAttribute("totalCount", totalCount);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext ctx = se.getSession().getServletContext();
		Integer totalCount = (Integer) ctx.getAttribute("totalCount");
		if (totalCount == null) {
			totalCount = 1;
		}
		totalCount--;
		System.out.println(totalCount);
		ctx.setAttribute("totalCount", totalCount);
	}
}
