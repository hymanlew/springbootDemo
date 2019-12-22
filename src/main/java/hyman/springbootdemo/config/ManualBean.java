package hyman.springbootdemo.config;

import hyman.springbootdemo.demo.DateJsonDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration：指明当前类是一个配置类；就是来替代之前的Spring配置文件。
 *
 * @Slf4j 是用作日志输出的，一般会在项目每个类的开头加入该注解，如果不写下面这段代码，并且想用 log，就需要该注解。
 * private final Logger logger = LoggerFactory.getLogger(当前类名.class);
 */
@Slf4j
@Configuration
public class ManualBean {

    /**
     * 将方法的返回值添加到容器中；容器中这个组件默认的 id 就是方法名。
     * @return
     */
    @Bean
    public DateJsonDemo testImport(){
        log.info("自定义配置类 @Bean 到容器中添加组件...");
        return new DateJsonDemo();
    }
}
