package hyman.springbootdemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return new DruidDataSource();
    }

    /**
     * 配置Druid的监控
     * 1、配置一个管理后台的Servlet
     * 2、配置一个web监控的filter
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet());

        Map<String,String> params = new HashMap<>();
        params.put("loginUsername","admin");
        params.put("loginPassword","admin");

        // 设置白名单，如果值为空，则代表所有主机都可以访问
        //params.put("allow","");
        params.put("allow","127.0.0.1");
        // 设置黑名单
        params.put("deny","");

        // 登录URL http://localhost:8080/d/login.html
        registrationBean.addUrlMappings("/druid/*");
        registrationBean.setInitParameters(params);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean filterBean = new FilterRegistrationBean(new WebStatFilter());

        Map<String,String> params = new HashMap<>();
        // 设置白名单，不拦截
        params.put("exclusions","*.js,*.css,/druid/*");

        // 拦截所有请求
        filterBean.setUrlPatterns(Arrays.asList("/*"));
        filterBean.setInitParameters(params);
        return filterBean;
    }
}
