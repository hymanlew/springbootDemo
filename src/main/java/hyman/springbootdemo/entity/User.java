package hyman.springbootdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
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
 * 以下注解的作用是告诉 jsonplug 组件，在将代理对象转换为 json 对象时，忽略value对应的数组中的属性，即（通过 java的反射机制将 pojo转换成 json的，属性，
 * 通过 java的反射机制将 pojo转换成 json的控制器）。如果你想在转换的时候继续忽略其他属性，可以在数组中继续加入。
 *
 * @JsonProperty： 可用来自定义属性名称；
 * @JsonIgnore： 可用来忽略不想输出某个属性；
 * @JsonInclude： 可用来动态包含属性，如可以不包含为 null 值的属性；
 *
 *
 * 返回 XML 数据：
 * 加入 XML 工具依赖：jackson-dataformat-xml 包。
 * 返回 xml 类型：@RequestMapping(value = "/test" produces = MediaType.APPLICATION_XML_VALUE)。
 * @JacksonXmlRootElement： 用在类上，用来自定义根节点名称；
 * @JacksonXmlProperty： 用在属性上，用来自定义子节点名称；
 * @JacksonXmlElementWrapper： 用在属性上，可以用来嵌套包装一层父节点，或者禁用此属性参与 XML 转换。
 *
 * 使用 redis 处理对象，必须实现序列化接口进行序列化。
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class User implements Serializable {

    /**
     * Bean Validation 中内置的 constraint： 
     @Null   被注释的元素必须为 null
     @NotNull    被注释的元素必须不为 null     
     @AssertTrue     被注释的元素必须为 true     
     @AssertFalse    被注释的元素必须为 false     
     @Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值     
     @Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值     
     @DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值     
     @DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值     
     @Size(max=, min=)   被注释的元素的大小必须在指定的范围内     
     @Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内     
     @Past   被注释的元素必须是一个过去的日期     
     @Future     被注释的元素必须是一个将来的日期     
     @Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式     

     Hibernate Validator 附加的 constraint：
     @NotBlank(message =)   验证字符串非null，且长度必须大于0     
     @Email  被注释的元素必须是电子邮箱地址     
     @Length(min=,max=)  被注释的字符串的大小必须在指定的范围内     
     @NotEmpty   被注释的字符串的必须非空     
     @Range(min=,max=,message=)  被注释的元素必须在合适的范围内  
     @CreditCardNumber     字符串必须通过Luhn校验算法（例如银行卡）
     */

    private Integer id;

    @JsonProperty("userName")
    @NotBlank(message="姓名不能为空！")
    private String Name;

    @NotNull(message = "性别为空")
    private Integer sex;

    @Max(value=100,message="年龄不能大于100岁")
    @Min(value=18,message="年龄必须满18岁")
    private Integer age;

    @NotEmpty(message="密码不能为空！")
    @Length(min=6,message="密码长度不能小于6位")
    private String Pass_word;

    @JsonIgnore
    @NotNull(message = "===")
    private Date birth;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String test;

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

    public String getPass_word() {
        return Pass_word;
    }

    public void setPass_word(String pass_word) {
        Pass_word = pass_word;
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

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", Pass_word='" + Pass_word + '\'' +
                ", birth=" + birth +
                ", test='" + test + '\'' +
                '}';
    }
}
