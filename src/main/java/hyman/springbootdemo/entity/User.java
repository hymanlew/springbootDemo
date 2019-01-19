package hyman.springbootdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

/**
 * spring boot 的参数校验支持：
 * 在日常开发中，最基本的校验有判断是否为空，长度是否符合要求，通常会用 if-else 进行判断或者使用正则表达式，而在 spring boot 中可以使用
 * (@Valibirthd User user, BindingResult bindingResult) 的方法更简单地解决。
 *
 * 以下注解全是基于 hibernate 框架的。
 */
/**
 * 当前项目由于配置了 JPA hibernate.ddl-auto，所以在应用启动的时候 hibernate 框架会自动去数据库中创建对应的表。persistence（持续；固执；存留）；
 *
 * @Entity 注解是 JPA herbinate 必须要有的，作用是声明一个实体类并与数据库表映射。也可以指定一个表映射 @Table。不写默认就是当前类名的小写，即 user。
 *
 * hibernate 会给每个被管理的 pojo 加入一个 hibernateLazyInitializer属性（并且 struts-jsonplugin 或者其他的 jsonplugin 都是）。而且 jsonplugin
 * 用的是 java的内审机制，jsonplugin 通过 java 的反射机制将 pojo 转换成 json，会把 hibernateLazyInitializer 也拿出来操作,但是 hibernateLazyInitializer
 * 无法由反射得到，所以就抛异常了。
 *
 * @JsonIgnoreProperties(value={“xxx”}) 注解是必须要加在 pojo 类上的，value 值就是要忽略的一些属性，这些属性是被 lazy加载的，也就是many-to-one的 one
 * 端的 pojo上。
 * 以下注解的作用是告诉 jsonplug 组件，在将代理对象转换为 json 对象时，忽略value对应的数组中的属性，即：通过 java的反射机制将 pojo转换成 json的，属性，
 * 通过 java的反射机制将 pojo转换成 json的控制器。
 *
 * 如果你想在转换的时候继续忽略其他属性，可以在数组中继续加入。
 *
 * 使用 redis 处理对象，必须实现序列化接口进行序列化。
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class User implements Serializable {

    private Integer id;

    @NotEmpty(message="姓名不能为空！")
    private String Name;

    private Integer sex;

    @Max(value=100,message="年龄不能大于100岁")
    @Min(value=18,message="年龄必须满18岁")
    private Integer age;

    @NotEmpty(message="密码不能为空！")
    @Length(min=6,message="密码长度不能小于6位")
    private String Pass_word;

    private Date birth;

    public User() {
    }

    public User(String name, String password) {
        this.Name = name;
        this.Pass_word = password;
    }

    public User(Integer id,String name, String password) {
        this.id = id;
        this.Name = name;
        this.Pass_word = password;
    }

    public User(Integer id,
                @NotEmpty(message = "姓名不能为空！") String name,
                Integer sex,
                @Max(value = 100, message = "年龄不能大于100岁") @Min(value = 18, message = "年龄必须满18岁") Integer age,
                @NotEmpty(message = "密码不能为空！") @Length(min = 6, message = "密码长度不能小于6位") String password,
                Date birth) {
        this.id = id;
        this.Name = name;
        this.sex = sex;
        this.age = age;
        this.Pass_word = password;
        this.birth = birth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return Pass_word;
    }

    public void setPassword(String password) {
        this.Pass_word = password;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + Name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", password='" + Pass_word + '\'' +
                ", birth=" + birth +
                '}';
    }
}
