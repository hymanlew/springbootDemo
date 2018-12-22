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
     * 1，通过SPRING INITIALIZR工具产生基础项目（是一个网站 http://start.spring.io/）
     * 2，选择构建工具Maven Project、Spring Boot版本等信息，Generate Project下载项目压缩包
     * 3，IntelJ 导入解压包：File –> New –> Project from Existing Sources –> Import project from external model –> Maven，
     *    点击 Next到最后。
     * 4，配置 pom.xml
     *
     * 1，也可以使用 intelJ 创建项目，new project -> spring Initaializr -> http://start.spring.io
     * 2，指定 springboot 版本（同样不可以用高版本），要导入的包，选择 core核心包，thymeleaf模板，等等
     * 3，其他步骤同上个一样
     *
     *
     * 在使用 springboot 时，没有了原来自己整合 Spring应用时繁多的 XML配置内容，替代它的是在 pom.xml中引入模块化的 Starter POMs，
     * 其中各个模块都有自己的默认配置，所以如果不是特殊应用场景，就只需要在 application.properties中完成一些属性配置就能开启各模
     * 块的应用。
     * application.properties 是整个应用程序的配置文件，SpringBoot自动加载，SpringBoot提供针对各种组件的都可以通过它进行配置。
     *
     *
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
     * 3，Spring Boot默认提供静态资源目录位置需置于 classpath下（即 resourse目录），目录名需符合如下规则：
     *    /static， /public， /resources， /META-INF/resources
     *
     *    举例：我们可以在 src/main/resources/目录下创建 static，并放置一个图片文件。启动程序后访问http://localhost:8080/D.jpg。
     *    如能显示图片，配置成功。
     *
     * 4，在 spring boot 中，有两种配置文件，一种是application.properties,另一种是application.yml,两种都可以配置spring boot
     *     项目中的一些变量的定义，参数的设置等。
     *     application.properties 配置文件在写的时候要写完整。而在yml 文件中配置则是使用冒号进行调用。
     *     application.yml 文件同样不允许有空格，和引号。
     *
     *     properties文件都需要写全，yml前面相同的可以不写，一层对应一层就好了。
     *     在yml文件中有些细节需要注意，冒号后面要空一格再写值，虽然在IDE中都会自动空一格。
     *
     * 5，yml文件的好处，天然的树状结构，一目了然，实质上跟properties是差不多的。官方给的很多demo，都是用yml文件配置的。
     * 6，注意点：
     *  1，原有的key，例如spring.jpa.properties.hibernate.dialect，按“.”分割，都变成树状的配置
     *  2，key后面的冒号，后面一定要跟一个空格
     *  3，把原有的application.properties删掉。然后一定要执行一下  maven -X clean install
     *  4，另外 properties 文件加载时是优先于 yml 文件的
     */
}
