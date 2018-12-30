package hyman.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * 该配置是整个程序的入口，由系统自动生成，不需要改动。（暂时不能启动这个主程序，因为 rabbitmq demo 重复定义了 class）
 *
 * SpringBootApplication，这个注解是一个组合注解，聚合了多个注解的功能：排除自启动项，排除自动启动的beanName，扫描包，扫描类，
 *
 * 其中的 @EnableAutoConfiguration 这个注解是用来启动SpringBoot中的自动配置项目，这个注解是必须加上的，否则无法正常使用因为SpringBoot。
 *
 * @EnableAutoConfiguration 这个注解是用来启动SpringBoot中的自动配置项目，这个注解是必须加上的，否则无法正常使用因为SpringBoot。
 * @MapperScan 如果是使用 xml dao 文件，则需要配置。如果使用 @dao 注解接口，则不需要配置
 * @EnableTransactionManagement 开启事务管理
 *
 * 在启动类中添加对mapper包扫描@MapperScan，或者直接在Mapper类上面添加注解@Mapper,建议使用上面那种，不然每个mapper加个注解也挺麻烦的。
 */

@MapperScan(basePackages = "hyman.springbootdemo.dao")
@SpringBootApplication(scanBasePackages = {"hyman.springbootdemo"})
@EnableCaching
@EnableTransactionManagement
public class SpringbootdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}
}
