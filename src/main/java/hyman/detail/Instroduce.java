package hyman.detail;

import org.thymeleaf.Thymeleaf;

public class Instroduce {
    /**
     * spring boot 是在现有Spring框架的基础上发布了一个创新的主要框架，其主要动机是简化配置和部署spring应用程序的过程。
     *
     * 使用Spring Boot将能够以更灵活的方式开发Spring应用程序，并且能够通过最小(或可能没有)配置Spring来专注于解决应用程序
     * 的功能需求。它使用全新的开发模型，通过避免一些繁琐的开发步骤和样板代码和配置，使Java开发非常容易。
     *
     * Spring Boot可以轻松创建单独的，生产级的基于Spring的应用程序，我们只管“运行”。查看Spring平台和第三方库。大多数Spring
     * Boot应用程序只需要很少的Spring配置。
     * Spring Boot 让我们的Spring应用变的更轻量化。比如你可以仅仅依靠一个Java类来运行一个Spring引用。你也可以打包你的应用为
     * jar并通过使用java -jar来运行你的Spring Web应用。
     *
     * SpringBoot在建立生产中的独立程序上非常简便、只需要一些简便的配置就能运行起来。大致有如下特点：
     *
     *     1，为所有Spring开发者更快的入门
     *     2，创建独立的Spring applications
     *     3，能够使用内嵌的Tomcat, Jetty or Undertow，不需要部署war。内嵌式容器简化Web项目
     *     4，提供starter pom来简化maven配置
     *     5，自动配置Spring。开箱即用，提供各种默认配置来简化项目配置
     *     6，提供一些生产环境的特性，比如metrics, health checks and externalized configuration
     *     7，绝对没有代码生成和XML配置要求。没有冗余代码生成和XML配置的要求
     *
     *
     * 在使用 springboot 时，没有了原来自己整合 Spring应用时繁多的 XML配置内容，替代它的是在 pom.xml中引入模块化的 Starter POMs，
     * 其中各个模块都有自己的默认配置，所以如果不是特殊应用场景，就只需要在 application.properties中完成一些属性配置就能开启各模
     * 块的应用。
     * application.properties 是整个应用程序的配置文件，SpringBoot自动加载，SpringBoot提供针对各种组件的都可以通过它进行配置。
     *
     * 注意事项：
     * 1，要将 Application类放在最外侧,即包含所有子包，因为 spring-boot 会自动加载启动类所在包下及其子包下的所有组件。即自定
     *    义的类及包与 application 类是平级关系。
     *
     * 2，在使用 @RestController来处理请求，返回的内容为json对象。那么在渲染 html页面的时候就需要使用模板引擎（例如 Thymeleaf）。
     *    Spring Boot 建议使用这些模板引擎，避免使用JSP，若一定要使用JSP将无法实现Spring Boot的多种特性。
     *    其模板引擎中的任何一个，它们默认的模板配置路径为：src/main/resources/templates。当然也可以修改这个路径，具体如何修改，
     *    可在后续各模板引擎的配置属性中查询并修改。
     *
     * 3，Spring Boot默认提供资源目录位置需置于 classpath下（即 resourse目录）：
     *    /static（静态资源），
     *    /template（模板页面，默认jar包使用嵌入式的Tomcat，默认不支持JSP页面）；可以使用模板引擎 freemarker、thymeleaf），
     *    application.properties，或 application.yml（全局的配置文件，配置文件名是固定的）。
     *
     * 4，在 spring boot 中，有两种配置文件，一种是application.properties,另一种是application.yml,两种都可以配置spring boot
     *     项目中的一些变量的定义，参数的设置等。
     *     application.properties 配置文件在写的时候要写完整。而在yml 文件中配置则是使用冒号进行调用。
     *     properties文件都需要写全，yml前面相同的可以不写，一层对应一层就好了。
     *
     * 5，yml文件的好处，天然的树状结构，一目了然，实质上跟properties是差不多的。官方给的很多demo，都是用yml文件配置的。
     *
     * 6，注意点：
     *  1，原有的key，例如spring.jpa.properties.hibernate.dialect，按“.”分割，都变成树状的配置。
     *  2，key后面的冒号，后面一定要跟一个空格。以空格的缩进来控制层级关系；只要是左对齐的一列数据，都是同一个层级的。
     *  3，属性和值也是大小写敏感的。字符串默认不用加上单引号或者双引号。
     *
     *  4，值加上双引号，不会转义字符串里面的特殊字符；而是将特殊字符本身表示的意思，转为字符串输出。"w \n c" 则输出 w 换行 c。
     *     值加上单引号，会转义特殊字符，即把特殊字符作为普通的字符串数据输出。"w \n c" 则输出 w \n c。
     *     对象、Map（属性和值）（键值对），数组（List、Set），详见配置文件。
     *
     *  5，把原有的application.properties删掉。然后一定要执行一下  maven -X clean install
     *  6，另外 properties 文件加载时是优先于 yml 文件的，即使是多环境下的配置文件也是如此。
     *
     * 7，YAML（YAML Ain't Markup Language），是一个标记语言，又不是一个标记语言。以前的配置文件；大多都使用的是 xxxx.xml文件；
     *    但是 YAML 是以数据为中心，比json、xml等更适合做配置文件；
     *
     *
     * Profile 是 spring 对不同环境（开发，测试，生产等环境）提供不同配置功能的支持，可以通过激活、指定参数等方式快速切换环境。
     * 1，使用多 profile 的文件形式，即 application-{profile环境标识}.properties/yml，在使用时只需要在主配置文件中激活即可。
     *    spring: profiles: active: dev
     *
     * 2，如果是使用 yml 的配置文件，可以使用三个横线（---）直接定义不同的配置文档块。使用 spring: profiles: prod 指定属于哪个环境。
     *    然后在最上面的文档块中，去激活指定的文档块。spring: profiles: active: dev2
     *
     * 3、激活指定profile：
     *    1、在配置文件中指定 spring.profiles.active=dev。
     *
     *    2、命令行（它优先于第一种）：
     *       开发测试时是在开发工具上方的 configaration（tomcat的位置） 中的 program argument 中写入命令，--spring.profiles.active=dev。
     *       在服务器启动部署时，使用 java -jar xxx.jar --spring.profiles.active=dev；
     *
     *    3、虚拟机参数，开发工具上方 configaration 中的 VM option 中写入命令：-Dspring.profiles.active=dev（固定写法）。
     *
     *
     * SpringBoot默认帮我们配置好了日志；如果需要使用自定义的配置文件，则在 resource 路径下放上每个日志框架自己的配置文件即可。
     * Logback：	logback-spring.xml , logback-spring.groovy , logback.xml（被日志框架直接识别） ，logback.groovy
     * Log4j2：		log4j2-spring.xml or log4j2.xml
     * Java Util Logging：	logging.properties
     *
     * 但推荐使用 logback-spring.xml，因为这样日志框架就不直接加载日志的配置项，而由SpringBoot解析日志配置，就可以使用 SpringBoot
     * 的高级 Profile 功能。即可以指定某段配置只在某个环境下生效。
     *
     *
     * 传统管理静态资源主要依赖于复制粘贴，不利于后期维护，所以 springboot 使用 WebJars对前端依赖进行统一管理！WebJars 是将 Web前端
     * Javascript和CSS 等资源打包成Java 的 Jar包，这样在 Java Web开发中我们可以借助 Maven 这些依赖库的管理，保证这些Web资源版本唯一性。
     *
     * "/**" 访问当前项目的任何资源，都去（静态资源的文件夹）找映射：
     * "classpath:/META‐INF/resources/",
     * "classpath:/resources/",
     * "classpath:/static/",
     * "classpath:/public/"， "/"：表示当前项目的根路径
     * 所有的 **斜杠favicon.ico（图标）都是在静态资源文件下找。
     *
     */

    /**
     * J2EE 定义了一个统一的缓存规范（JSR107），Java Caching 定义了5个核心接口，分别是CachingProvider, CacheManager, Cache, Entry 和
     * Expiry：
     *
     * 1，CachingProvider 定义了创建、配置、获取、管理和控制多个CacheManager。一个应用可以在运行期访问多个CachingProvider。
     * 2，CacheManager定义了创建、配置、获取、管理和控制多个唯一命名的Cache，这些Cache存在于CacheManager的上下文中。一个 CacheManager仅
     *    被一个CachingProvider所拥有。
     * 3，Cache是一个类似Map的数据结构并临时存储以Key为索引的值。一个Cache仅被一个CacheManager所拥有。
     * 4，Entry是一个存储在Cache中的key-value对。
     * 5，Expiry是每一个存储在Cache中的条目有一个定义的有效期。一旦超过这个时间，条目为过期的状态。一旦过期条目将不可访问、更新和删除。缓
     *    存有效期可以通过ExpiryPolicy设置。
     *
     *
     * springboot 缓存抽象：
     * 但是并不是所有的缓存组件（系统）都实现了 JSR107 的实现，所以它使用的很少。springboot 提供了自己的缓存抽象，原理与 JSR107 一样。
     * Spring 从3.1开始定义了 org.springframework.cache.Cache 和 org.springframework.cache.CacheManager 接口来统一不同的缓存技术。
     * 并支持使用JCache（JSR-107）注解简化我们开发。
     *
     * Cache 接口为缓存的组件规范定义，包含缓存的各种操作集合；
     * Cache接口下 Spring提供了各种 xxxCache的实现；如RedisCache，EhCacheCache , ConcurrentMapCache等。
     *
     * 每次调用需要缓存功能的方法时，Spring会检查检查指定参数的指定的目标方法是否已经被调用过；如果有就直接从缓存中获取方法调用后的结果，如果
     * 没有就调用方法并缓存结果后返回给用户。下次调用直接从缓存中获取。
     *
     * 使用Spring缓存抽象时我们需要关注两点：1、确定方法需要被缓存以及他们的缓存策略。2、从缓存中读取之前缓存存储的数据。
     *
     * Cache		缓存接口，定义缓存操作。实现有：RedisCache、EhCacheCache、ConcurrentMapCache等
     * CacheManager	缓存管理器，管理各种缓存（Cache）组件
     * @Cacheable	主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
     * @CacheEvict	针对方法配置（例如删除方法），对其缓存中的数据进行清空。
     * @CachePut	针对方法配置，保证方法被调用，又希望结果被缓存。
     * @EnableCaching	开启基于注解的缓存，使用以上注解时必须加上这个。
     * keyGenerator	缓存数据时 key生成策略
     * serialize		缓存数据时 value序列化策略
     */

    /**
     * springboot 2 使用监控功能，端口号加上 /actuator 才能访问相应的端点，只能访问添加过的端点：
     * 端点名	    描述
     * autoconfig	所有自动配置信息，已经启用的
     * auditevents	审计事件
     * beans	    所有Bean的信息
     * configprops	所有配置属性
     * dump	        线程状态信息
     * env	        当前环境信息
     * health	    应用健康状况
     * info	        当前应用信息
     * metrics	    应用的各项指标，性能指标
     * mappings	    应用 @RequestMapping 映射路径
     * shutdown	    关闭当前应用（默认关闭），使用 post 方式远程关闭当前系统
     * trace	    追踪信息（最新的http请求）
     */
}