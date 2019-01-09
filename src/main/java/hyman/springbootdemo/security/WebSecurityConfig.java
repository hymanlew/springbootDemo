package hyman.springbootdemo.security;

import hyman.springbootdemo.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Configuration 注解就是用于自定义配置文件的。
 * <p>
 * 由于 spring boot是自动加载并装配配置文件的，所以只要引入了 security jar包，就算把这个类都注解掉了，但是项目启动
 * 后访问还是要进行认证的，只不过是采用框架默认的认证体系，密码也是被加过密了。
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @param auth
     * @throws Exception
     * @Resource ，使用该注解使系统自动连接注入到自定义的用户管理器，AuthenticationManagerBuilder。
     */
    @Resource
    public void configHandler(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 先暂时指定一个用户，并设置为最高权限 admin。
         *
         * 设置内存指定的登录的账号密码，指定角色。并且不加 .passwordEncoder(new MyPasswordEncoder())，就不是以明文的方式进
         * 行匹配，会报错。
         *
         * 使用 passwordEncoder 后，在页面提交时候，密码以明文的方式进行匹配。
         *
         */
        List<User> list = new ArrayList<>();
        list.add(new User(1, "admin", "123456"));
        list.add(new User(2, "user", "123456"));
        for (User user : list) {
            auth
                    .inMemoryAuthentication()
                    .withUser(user.getName()).password(user.getPassword()).roles("ADMIN");
            auth
                    .inMemoryAuthentication()
                    .passwordEncoder(new MyPasswordEncoder())
                    .withUser(user.getName()).password(user.getPassword()).roles("ADMIN");
        }
    }

    /**
     * 如果不重写此方法，则 security 系统会自动生成一个登录页面，且对所有页面都进行拦截。
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 设置登录,注销，表单登录不用拦截，其他请求要拦截。
         * antMatchers：逐步解析比对用户的请求。
         *
         * 利用流的方式来定义安全策略:
         * 1，通过 authorizeRequests() 定义哪些 URL需要被保护、哪些不需要被保护。例如下面的代码指定了主页不需要任何认证就
         *    可以访问，其他的路径都必须通过身份验证。
         * 2，通过 formLogin() 定义当需要用户登录时候，转到的登录页面（loginPage）。
         * 3，并且必须自定义登录验证路径，否则不能正常登录，因为缺少配置。并且不能是根目录
         */

        http
            .authorizeRequests()
                // 主页，或登录页面不设限制
                .antMatchers("/login").permitAll()
                .antMatchers("/client/**").permitAll()
                //.antMatchers("/security/**").access("hasRole('ADMIN') and hasRole('USER')")
                .antMatchers("/security/**").hasAnyRole("ADMIN")
                .antMatchers("/demo/**").hasAnyRole("ADMIN")
            // 任何请求,登录后可以访问
            .anyRequest()
                .authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                //可以自定义 form 表单提交的参数
                .usernameParameter("username")
                .passwordParameter("password")
                // 该路径必须与登录页面，控制器方法保持一致（它是显示在浏览器上的路径）。否则会 302 重定向，或 404。
                .loginProcessingUrl("/security")
                // 必须指定登录成功的连接，并带上数据。否则默认会到根目录
                .successHandler(new SimpleUrlAuthenticationSuccessHandler("/security/login"))

                //也可以不指定登录成功的页面路径，因为它是重定向，所以 request 中的数据就会没有了
                //.successForwardUrl("/security/welcome")
                //同样也可以自定义登录成功的页面路径，但它也是重定向，所以 request 中的数据就会没有了
                //.successHandler(new MyUrlSuccessHandler())

                //如果用户没有访问受保护的页面，默认跳转到页面
                //.defaultSuccessUrl("/security/success")
                .failureForwardUrl("/parselogin?error=yes")
            .and()
            .logout()
                .logoutSuccessUrl("/parselogin?logout=yes")
                .permitAll();

        //默认开启，关闭默认的 csrf 认证
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置静态资源不要拦截，定义为 resource 下的文件夹
        web.ignoring().antMatchers("classpath:/static/**");
    }
}
