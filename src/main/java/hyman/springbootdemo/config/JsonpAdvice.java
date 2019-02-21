//package hyman.springbootdemo.config;
//
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.json.MappingJacksonValue;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//
//import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;
//
//import javax.servlet.http.HttpServletRequest;
//
/**
 * 使用jsonp实现跨域的支持，该方式是用于spring4 版本的，spring5 已经弃用，而是直接在 MVCconfig 中 addCorsMappings 加 @CrossOrigin注解。
 *
 * 参考：https://mp.weixin.qq.com/s?__biz=MzAxNDMwMTMwMw==&mid=2247490417&idx=1&sn=0f3dfbb8f9ec5842565c2affb213bef2&
 * chksm=9b943a69ace3b37fa476a9a9a1315a24b7cde2ac1a6db7ed708924502f7977efad69e7313922&scene=0&xtrack=1#rd
 *
 * $.ajax({
     type : "get/post",
     async:false,
     dataType:'jsonp',
     url: 'http://sso.isy.cn/login.json',
     data: $("#loginForm").serialize(),
     crossDomain: true,
     jsonpCallback:"callback",
     xhrFields: {
        withCredentials: true   //注意这里必须指定，否则cookie无法传递过去
     },
     success : function(data){
     },
     error:function(data){
         console.log("登录出错");
         $.we.utils.gotoUrl("/");
     }
 });
 */
//@ControllerAdvice(basePackages = "hyman.springbootdemo.controller")
//public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {

//     public JsonpAdvice() {
//       接收 jsonp请求的参数 key，url 中 callback参数
//       super("callback");
//   }

//    还可以重写以下方法，实现 url携带 callback就返回 jsonp格式，没有就返回正常格式
//    @Override
//    protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {
//        HttpServletRequest hrequest = (HttpServletRequest)request;
//
//        // 如果不存在 callback 这个请求参数，则直接返回，不需要处理为 jsonp。
//        if(ObjectUtils.isEmpty(hrequest.getParameter("callback"))){
//            return;
//        }
//    }
//}
