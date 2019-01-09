package hyman.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

// springboot 扩展 springMVC 的功能配置类

//@EnableWebMvc，不可以标注该注解，因为它代表了全面接管 MVC 的操作实现。即弃用 boot 的默认配置。
@Configuration
public class MyMVCconfig implements WebMvcConfigurer{

    // 自定义视图解析器，可以添加多个， registry.addViewController。
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /**
         * Spring Boot提供了一个默认的映射：/error，当处理中抛出异常之后，会转到该请求中处理，并且该请求有一个全局的错误页面用
         * 来展示异常内容。默认 SpringBoot 就会去找到一个页面 error/状态码：
         * 模板引擎可用的情况下会返回到 errorViewName 指定的视图地址，即引擎文件夹下的 error/状态码 页面。
         * 模板引擎不可用时，就在静态资源文件夹下找 errorViewName 对应的页面 error/404.html。
         *
         * 在有精准匹配的页面时，就会返回与状态码对应的页面（如 404 对应 404 名称的页面）。如果没有对应的页面时就会根据状态码
         * 找公共的页面（如 4 开头的状态码找 4xx 页面，5 开头的状态码找 5xx 页面）。
         * 当然这些是在没有自定义统一异常处理器时的流程。
         *
         * 所以这里只是演示一下自定义视图器的功能。
         */
        //registry.addViewController("/error/**").setViewName("/error/**");
    }

    // 自定义区域信息解析器，显示语言
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    // 自定义拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // SpringBoot已经做好了静态资源映射，所以不需要单独配置
        registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login","/parselogin");
    }
}

/**
 * 使用以下继承的方式会导致 boot 自动配置失效的问题，例如配置的静态资源路径，date 格式等等。这是因为在 springboot 的web自动配
 * 置类 WebMvcAutoConfiguration 上有条件注解 @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)，只有在缺少
 * WebMvcConfigurationSupport 类型的 bean时改自动配置类才会生效，所以继承下面的类后需要自己再重写相应的方法。
 * 该方法等同于标注了 @EnableWebMvc 注解。
 *
 * 如果想要使用自动配置生效，又要按自己的需要重写某些方法，比如增加 viewController，则可以继承 WebMvcConfigurerAdapter 这个类。
 * 不过在spring5.0 版本后这个类被丢弃了，所以我们在自定义配置类中直接实现 WebMvcConfigurer 即可。
 */
//public class MyMVCconfig extends WebMvcConfigurationSupport {
//
//}