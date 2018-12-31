package hyman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * 该配置是整个程序的入口，由系统自动生成，不需要改动。
 *
 * SpringBootApplication，这个注解是一个组合注解，聚合了多个注解的功能：
 *
 * @SpringBootConfiguration 注解，标注在某个类上，表示这是一个Spring Boot的配置类。
 * 		@Configuration 配置类上标注这个注解；即是将 spring 中的配置文件声明为配置类。配置类也就是容器中的一个组件，即@Component。
 *
 * @EnableAutoConfiguration 开启自动配置功能，这样自动配置才能生效，正常启动。
 * 		@AutoConfigurationPackage 自动配置包。
 * 			@Import(AutoConfigurationPackages.Registrar.class) Spring的底层注解@Import，给容器中导入一个组件；导入的组件由括号
 * 			内指定的	.class 类将主配置类（@SpringBootApplication标注的类）的所在包及下面所有子包里面的所有组件扫描到Spring容器。
 *
 * 		    @Import(EnableAutoConfigurationImportSelector.class) 将所有需要导入的组件以全类名的方式返回；这些组件就会被添加到容器中；
 * 		    会给容器中导入非常多的自动配置类（xxxAutoConfiguration）；就是给容器中导入这个场景需要的所有组件，并配置好这些组件；
 *
 * 有了自动配置类，免去了我们手动编写配置注入功能组件等的工作。Spring Boot在启动的时候从类路径下的META-INF/spring.factories中获取
 * EnableAutoConfiguration指定的值，将这些值作为自动配置类导入到容器中，自动配置类就生效，帮我们进行自动配置工作；以前我们需要自己
 * 配置的东西，自动配置类都帮我们；
 * J2EE的整体整合解决方案和自动配置都在 spring-boot-autoconfigure-版本号.RELEASE.jar 中；
 *
 * @ImportResource 导入外部的 Spring 配置文件（xml），让其生效。因为在 Spring Boot 里面默认没有 Spring的配置文件，我们自己编写的配置
 * 文件，也不能自动识别。这时就要使用该注解将其加载进来。@ImportResource 标注在一个配置类上即可。
 * 但是这种方式是老的，所以建议使用 configuration + bean 注解类的方式生成 bean 对象（config 包中）。
 *
 * @MapperScan 如果是使用 xml dao 文件，则需要配置。如果使用 @dao 注解接口，则不需要配置
 * @EnableTransactionManagement 开启事务管理
 *
 * 在启动类中添加对mapper包扫描@MapperScan，或者直接在Mapper类上面添加注解@Mapper,建议使用上面那种，不然每个mapper加个注解也挺麻烦的。
 */

//@ImportResource(locations = {"classpath:beans.xml"})
@MapperScan(basePackages = "hyman.springbootdemo.dao")
//@SpringBootApplication(scanBasePackages = {"hyman.springbootdemo.**","hyman.springbootdemo.util"})
//@SpringBootApplication(scanBasePackages = {"hyman.springbootdemo.util"})
@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
public class SpringbootdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}
}
