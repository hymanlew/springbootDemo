package hyman.springbootdemo.security;

import hyman.springbootdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Configuration 注解就是用于自定义配置文件的。
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * @Resource ，使用该注解使系统自动连接注入到自定义的用户管理器，AuthenticationManagerBuilder。
     * @param auth
     * @throws Exception
     */
    @Resource
    public void configHandler(AuthenticationManagerBuilder auth) throws Exception {
        // 先暂时指定一个用户，并设置为最高权限 admin。
        List<User> list = new ArrayList<>();
        list.add(new User("admin",123456));
        list.add(new User("user",123456));
        for(User user:list){
            auth
                .inMemoryAuthentication()
                    .withUser(user.getName()).password(user.getAge()+"").roles("ADMIN");
        }

    }

    /**
     * 如果不重写此方法，则 security 系统会自动生成一个登录页面，且对所有页面都进行拦截。
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * antMatchers：逐步解析比对用户的请求。
         *
         * 利用流的方式来定义安全策略:
         * 1，通过 authorizeRequests() 定义哪些 URL需要被保护、哪些不需要被保护。例如下面的代码指定了主页不需要任何认证就
         *    可以访问，其他的路径都必须通过身份验证。
         * 2，通过 formLogin() 定义当需要用户登录时候，转到的登录页面（loginPage）。
         * 3，并且必须自定义登录验证路径，否则不能正常登录，因为缺少配置。并且不能是根目录
         */
        http
            .formLogin()
                .loginPage("/dologin")
                .permitAll()
                .loginProcessingUrl("/parselogin")
                .failureUrl("/parselogin?error=yes")
                .and()
                .authorizeRequests()
                // 主页，或登录页面不设限制
                .antMatchers("/do/test").permitAll()
                .anyRequest()
                .authenticated()
                .and()
            .logout()
                .logoutSuccessUrl("/parselogin?logout=yes")
                //.logoutSuccessUrl("/login?logout")
                .permitAll();
    }


}