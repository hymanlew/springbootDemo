package hyman.springbootdemo.filter;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class WebConfig {

    @Bean
    public RemoteIpFilter remoteIpFilter(){
        return new RemoteIpFilter();
    }

    /**
     * Spring 提供了FilterRegistrationBean类，可以为自定义 Filter设置执行顺序。
     * @return
     */
    @Bean
    public FilterRegistrationBean testFilterRegist(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName","paramValue");
        registration.setName("MyFilter");

        // 为 Filter 设置排序，为第一位
        registration.setOrder(1);
        return registration;
    }
}
