package hyman.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 * 该配置是整个程序的入口，由系统自动生成，不需要改动。
 *
 * SpringBootApplication，这个注解是一个组合注解，聚合了多个注解的功能：排除自启动项，排除自动启动的beanName，扫描包，扫描类，
 *
 * 其中的 @EnableAutoConfiguration 这个注解是用来启动SpringBoot中的自动配置项目，这个注解是必须加上的，否则无法正常使用因为SpringBoot。
 *
 * @EnableAutoConfiguration 这个注解是用来启动SpringBoot中的自动配置项目，这个注解是必须加上的，否则无法正常使用因为SpringBoot。
 * @MapperScan 如果是使用 xml mapper 文件，则需要配置。如果使用 @mapper 注解接口，则不需要配置
 */

//@MapperScan(basePackages = "classpath:mapper")
@SpringBootApplication
@EnableCaching
public class SpringbootdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}
}
