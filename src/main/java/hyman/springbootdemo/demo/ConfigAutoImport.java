package hyman.springbootdemo.demo;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Inherited 注解指明被标注的类会自动被继承，即当在自定义的注解上标识 @Inherited 注解后，然后用自定义的注解标注一个父类时，而此父类又有
 * 一个子类时，则父类的所有属性及方法都会被继承到子类中，并且不并显示地实现抽象方法。
 * 如果不使用 @Inherited 标注父类的话，则子类继承时，必须显示地重写其抽象方法，否则报错。
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(ConfigImport.class)
public @interface ConfigAutoImport {
}
