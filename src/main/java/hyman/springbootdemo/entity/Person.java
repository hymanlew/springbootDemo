package hyman.springbootdemo.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中（用于 yml 配置文件），该方式的缺点是不支持 SpEL EL 表达式。并且它有一个
 * 要求，就是这些属性配置必须是写在系统默认的配置文件中的，即 application 中。
 *
 * @ConfigurationProperties 告诉 SpringBoot 将本类中的所有属性和配置文件中相关的配置进行绑定；
 * prefix = "person"：配置文件中哪个下面的所有属性进行一一映射。
 *
 * @PropertySource(value = {"path"}) 如果属性的配置不是写在系统默认的配置文件中，则就需要指定该属性文件的路径引入加载。
 *
 * 只有这个组件是容器中的组件 @Component，才能使用容器提供的 @ConfigurationProperties功能；
 *
 *
 * @Validated（属于 Spring Validation验证框架），@Valid（属于 javax），两者在检验 Controller 的入参是否符合规范，及基本验证
 * 功能上没有太多区别。但是在分组、注解地方、嵌套验证等功能上两个有所不同：
 *
 * @Validated：提供了一个分组功能，可以在入参验证时，根据不同的分组采用不同的验证机制。
 * @Valid：作为标准JSR-303规范，还没有吸收分组的功能。
 *
 * @Validated：可以用在类型、方法和方法参数上。但是不能用在成员属性（字段）上。
 * @Valid：可以用在方法、构造函数、方法参数和成员属性（字段）上。因为可以用于成员属性上所以能提供嵌套验证（即集合内元素
 * 的验证）的功能。
 *
 * 默认两者加在方法参数前，都不会自动对参数进行嵌套验证（即都无法单独提供嵌套验证）。所以必须手动在实体类的集合字段上明确指出这
 * 个集合里面的实体也要进行验证。并且必须加上 @Valid 注解。然后在 Controller的方法参数上再使用 @Validated或者@Valid，就能对参
 * 数的入参进行嵌套验证。此时如果相应字段为空的情况，Spring Validation框架就会检测出来，bindingResult就会记录相应的错误。
 *
 * 并且在方法中使用验证时，@Valid 的参数后必须紧挨着一个BindingResult 参数，否则spring会在校验不通过时直接抛出异常。
 */
@Component
@ConfigurationProperties(prefix = "person")
//@PropertySource(value = {"classpath:xxx.properties"})
public class Person {

    //@Email
    private String lastName;

    @NotBlank(message = "不能为空")
    @Min(value = 1,message = "不能小于1")
    private Integer age;

    private Boolean boss;

    @NotNull(message = "日期不能为空")
    private Date birth;

    private Map<String,Object> maps;

    // 嵌套验证必须用 @Valid
    @Valid
    @Size(min = 0,message = "list 长度")
    private List<Object> lists;

    @Valid
    private Message message;

    public Person() {
    }

    public Person(String lastName, Integer age) {
        this.lastName = lastName;
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", message=" + message +
                '}';
    }
}
