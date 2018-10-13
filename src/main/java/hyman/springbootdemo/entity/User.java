package hyman.springbootdemo.entity;

<<<<<<< HEAD
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * spring boot 的参数校验支持：
 * 在日常开发中，最基本的校验有判断是否为空，长度是否符合要求，通常会用 if-else 进行判断或者使用正则表达式，而在 spring boot 中可以使用
 * (@Validated User user, BindingResult bindingResult) 的方法更简单地解决。
 *
 * 以下注解全是基于 hibernate 框架的。
 */
public class User {

    private Integer id;

    @NotEmpty(message="姓名不能为空！")
    private String name;

    @Max(value=100,message="年龄不能大于100岁")
    @Min(value=18,message="年龄必须满18岁")
    private Integer age;

    @NotEmpty(message="密码不能为空！")
    @Length(min=6,message="密码长度不能小于6位")
    private String password;

=======
public class User {

    private Integer id;
    private String name;
    private Integer age;

>>>>>>> fc86736a21522464b4b9832f277ec1070351a2f7
    public User() {
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
