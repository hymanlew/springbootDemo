package hyman.detail;

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
     */
}