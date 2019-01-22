package hyman.springbootdemo.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
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
 * @Validated 数据校验。例如校验邮箱 @Email。
 * @PropertySource(value = {"path"}) 如果属性的配置不是写在系统默认的配置文件中，则就需要指定该属性文件的路径引入加载。
 *
 * 只有这个组件是容器中的组件 @Component，才能容器提供的 @ConfigurationProperties功能；
 *
 */
@Component
@ConfigurationProperties(prefix = "person")
//@PropertySource(value = {"classpath:xxx.properties"})
@Validated
public class Person {

    //@Email
    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;
    private Map<String,Object> maps;
    private List<Object> lists;
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
