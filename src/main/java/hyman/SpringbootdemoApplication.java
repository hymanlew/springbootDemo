package hyman;

import com.sun.istack.internal.Interned;
import hyman.springbootdemo.demo.ConfigAutoImport;
import hyman.springbootdemo.demo.ConfigClass;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.lang.annotation.*;
import java.util.Locale;


/**
 * 该配置是整个程序的入口，由系统自动生成，不需要改动。
 *
 * SpringBootApplication，这个注解是一个组合注解，聚合了多个注解的功能（它会自动扫描同目录及下级目录下的所有配置类）：
 *
 * @SpringBootConfiguration 注解，标注在某个类上，表示这是一个Spring Boot的配置类。
 * 		@Configuration 配置类上标注这个注解；即是将 spring 中的配置文件声明为配置类。配置类也就是容器中的一个组件，即@Component。
 *
 * @EnableAutoConfiguration 开启自动配置功能，这样自动配置才能生效，正常启动。
 * 		@AutoConfigurationPackage 自动配置包 = @Import(AutoConfigurationPackages.Registrar.class)。
 *
 * 		 @Import() 它是 Spring 的底层注解，是给容器中导入一个组件；导入的组件由括号内指定的	.class 类将主配置类（@SpringBootApplication
 * 		 标注的类）的所在包及下面所有子包里面的所有组件扫描到Spring容器。
 *
 * 		 @Import(AutoConfigurationImportSelector.class) 括号中的类实现了 importSelector 接口，且该接口只有一个 selectImports 抽象方法，
 * 		 它是将所有需要导入的组件以全类名的方式返回（返回一个 string 数组，这个数组中指定了需要装配到 IOC 容器的类），会给容器中导入非常多
 * 		 的自动配置类（xxxAutoConfiguration）；当在 @Import 中导入一个 importSelector 实现类之后，就会把该实现类中返回的 class 名称对应
 * 		 的类都装载到 IOC 容器中；就是给容器中导入这个场景需要的所有组件，并配置好这些组件；
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
 *
 * @EnableRabbit + @RabbitListener(queues = "xxx")，它会自动监听并获取到指定队列中的消息。
 */

/**
 * 当引入 redis-starter 后，容器中保存的是 RedisCashManager，并且它创建 rediscache 作为缓存组件。rediscache 通过操作 redis 来缓存数据。
 * 即一旦引入 redis-starter，则 springboot 就默认把 redis 当作缓存，它是全自动配置的。rediscache 使用 redistemplate 来处理数据，但其对
 * 象的保存默认都是使用 JDK 序列化机制，所以需要自定义 RedisCashManager。
 *
 * 在本项目中启动暂时关闭相关功能，因为是实验 demo 服务器没有默认启动，所以项目启动会一直尝试连接服务器，而启动太慢。
 *
 * @EnableRedisRepositories （Redis Repositories），使用仓储可以实现Redis Hashs与领域对象无缝的转换和存储，应用自定义的映射策略和使用二
 * 级索引。Redis的仓储需要至少Redis 2.8.0版本。
 * 利用仓储的支持可以很轻松的访问存储在Redis 里的领域实体。
 */

//@ImportResource(locations = {"classpath:beans.xml"})
@MapperScan(basePackages = "hyman.springbootdemo.dao")
@SpringBootApplication

//@EnableCaching
//@EnableRabbit
@EnableTransactionManagement
@EnableRedisRepositories

//自定义的批量自动装配
@ConfigAutoImport
public class SpringbootdemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringbootdemoApplication.class, args);
		ConfigClass config = context.getBean(ConfigClass.class);
		System.out.println(config.getClass());
	}

}
