package hyman.springbootdemo.config;

import hyman.springbootdemo.demo.DateJsonDemo;
import hyman.springbootdemo.util.Logutil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration：指明当前类是一个配置类；就是来替代之前的Spring配置文件
 *
 */

@Configuration
public class ManualBean {

    // 将方法的返回值添加到容器中；容器中这个组件默认的 id 就是方法名。
    @Bean
    public DateJsonDemo testImport(){
        Logutil.logger.info("自定义配置类 @Bean 到容器中添加组件...");
        return new DateJsonDemo();
    }
}
