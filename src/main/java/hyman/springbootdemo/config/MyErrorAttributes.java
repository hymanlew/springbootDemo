package hyman.springbootdemo.config;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 自定义自适应的错误响应，数据或是 json 数据到客户端，页面。通过定制 ErrorAttributes 改变需要返回的内容。
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes{

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        /**
         * 要注意这里要么全部自定义 key-value（固定值，或从 request 中获取，但是这样就跟源码重复了），要么就先获取父类的 map 然后再修改（包括增加 key 属性）。
         */
        //Map<String, Object> errorAttributes = new LinkedHashMap();
        //errorAttributes.put("key", "value");
        //try {
        //    errorAttributes.put("error", HttpStatus.valueOf(status).getReasonPhrase());
        //} catch (Exception var5) {
        //    errorAttributes.put("error", "Http Status " + status);


        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest,includeStackTrace);
        // 可以前期的自定义的异常处理中，往 request 中存入数据。但前提是系统走自定义的异常处理器（例如自定义异常）。
        // errorAttributes.put("message", webRequest.getAttribute("message",0));
        errorAttributes.put("add", "addvalue");
        errorAttributes.remove("timestamp");
        return errorAttributes;
    }
}
