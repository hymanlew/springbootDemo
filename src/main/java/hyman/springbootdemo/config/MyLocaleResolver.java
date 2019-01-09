package hyman.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 页面中国际化语言的显示：
 * 1，在 springMVC 中需要三步：1，编写国际化配置文件。2，使用 ResourceBundleMessageSource 管理国际化资源文件。3，在页面使用fmt:message取出国际化内容。
 *
 * 2，在 boot resource 下直接创建国际化文件夹，然后创建 xx.properties，xx_zh_CN.properties，xx_en_US.properties，之后它自动就会被识别为国际化配置文件。
 *    然后就可以直接打开在 resource bundle 中添加不同语言显示的信息。最后在全局配置文件中加入 spring.messages.basename=internal.login（国际化文件夹.文
 *    件名），而且 SpringBoot 已经自动配置好了管理国际化资源文件的组件；
 *
 * 3，在页面中用 thymeleaf 国际化标签 #{} 接收信息，然后页面就能根据浏览器语言设置切换国际化语言。因为在浏览器发送请求时，header 中会带有浏览器的语言信息
 *   （Accept-Language），然后经过 spring 的区域信息解析器（localeResolver）返回语言的相关信息（默认的就是根据请求头带来的区域信息获取 Locale（区域信息
 *   对象）进行国际化，并且默认是固定值）。
 *
 * 4，因此可以自定义区域信息解析器来自定义语言的显示，
 *
 */
public class MyLocaleResolver implements LocaleResolver{
    // 解析区域信息
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String language = httpServletRequest.getParameter("lang");
        Locale locale = Locale.getDefault();

        if(!StringUtils.isEmpty(language)){
            String[] data = language.split("_");

            // Locale(String language, String country)
            locale = new Locale(data[0],data[1]);
        }
        return locale;
    }

    // 设置区域信息
    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
