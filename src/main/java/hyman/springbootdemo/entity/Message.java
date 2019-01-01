package hyman.springbootdemo.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 如果我们只是在某个业务逻辑中需要获取一下配置文件中的某项值，使用@Value；
 * 如果是专门编写了一个 javaBean 来和配置文件进行映射，则就直接使用 @ConfigurationProperties；
 */

@Component
public class Message {

    // 读取配置文件中的值，与信息，必须配置 get、set 方法（该方式是老的方式，除了支持 EL 表达式，其他都不如 ConfigurationProperties
    // 注解，即不支持它里面的其他功能）。
    @Value("${title}")
    private String title;

    @Value("${description}")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Message{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
