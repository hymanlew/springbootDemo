package hyman.springbootdemo.demo;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * importSelector 接口只有一个 selectImports 抽象方法，它是将所有需要导入的组件以全类名的方式返回（返回一个 string 数组，这个数组中
 * 指定了需要装配到 IOC 容器的类），会给容器中导入非常多的自动配置类（xxxAutoConfiguration）；当在 @Import 中导入一个 importSelector
 * 实现类之后，就会把该实现类中返回的 class 名称对应的类都装载到 IOC 容器中；就是给容器中导入这个场景需要的所有组件，并配置好这些组件；
 *
 * 和 @Configuration 不同的是，importSelector 可以实现批量装配，还可以通过逻辑处理来实现 Bean 的选择性装配，也就是可以根据上下文来决
 * 定哪些类能够被 IOC 容器初始化。
 *
 * 这种方式比 @Import(Configuration.class) 的好处在于装配的灵活性，还可以实现批量装配。
 *
 * 由此得出，自动装配的核心是扫描约定目录下的文件进行解析，解析完成之后把得到的 Configuration 配置类通过 ImportSelector 进行导入，从而
 * 完成 Bean 的自动装配过程。在 spring 中是使用的 AutoConfigurationImportSelector 类，它实现了 ImportSelector 接口。
 * 是从 META-INF/spring-autoconfigure-metadata.properties 中加载自动装载的条件元数据，只有满足条件的 bean 才会进行装配。其作用是跟
 * @Conditional 注解一样的，它只是将这些条件配置在了配置文件中。
 */
public class ConfigImport  implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{ConfigClass.class.getName()};
    }
}
