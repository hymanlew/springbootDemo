package hyman.springbootdemo.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Spring boot 2.0.3 引用的security 依赖是spring security 5.X版本，此版本需要提供一个PasswordEncorder的实例，否则后台汇报
 * 错误：
 * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"， 并且页面毫无响应。
 *
 * 新的密码存储格式为：加密方式和加密后的密码。所以要指定一个加密方式
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder{
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
