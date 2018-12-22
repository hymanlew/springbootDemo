package hyman.springbootdemo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 使用系统自身的测试类，编写一个简单的单元测试来模拟 http 请求
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class SpringbootdemoApplicationTests {

    /**
     * perform：执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；
     *
     * andExpect：添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确；
     *
     * andDo：添加ResultHandler结果处理器，比如调试时打印结果到控制台；
     *
     * andReturn：最后返回相应的MvcResult；然后进行自定义验证/进行下一步的异步处理；
     */

    /**
     * test 包自带的，模拟测试，无须启动服务器，MockMvc 是模拟 http 请求的（get，post）
     */
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    /**
     * 使用 mockmvc 构造器构造一个 mvc，它有两个实现的方法：
     * 1，standaloneSetup：是要对应到要测试的类，然后生成一个构造方法。
     * 2，webAppContextSetup：是使用系统上下文来生成一个构造 mvc。
     * <p>
     * 所以最好要使用第二种。
     */
    @Before
    public void setup() {
        // 使用 build 对应到要测试的类
        //mvc = MockMvcBuilders.standaloneSetup(new DemoController()).build();
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    /**
     * 使用 MockServletContext来构建一个空的 WebApplicationContext，这样我们创建的 DemoController就可以在 @Before函数中
     * 创建并传递到 MockMvcBuilders.standaloneSetup（）函数中。
     * <p>
     * 其中，equalto 的值必须与响应是相同的，否则报错
     *
     * @throws Exception
     */
    @Test
    public void index() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/demo/index")
                .accept(MediaType.APPLICATION_JSON_UTF8)).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
        // 这里的 content 是指定且是固定比对要输出的内容，与上面的 print 相冲突，所以先注释掉
        //.andExpect(content().string(equalTo("hello world you")));
        mvc.perform(MockMvcRequestBuilders.get("/demo/index").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("hello world you")));
    }

    @Test
    public void userTest() throws Exception {
        RequestBuilder request = null;

        // 1、使用 get 请求查一下user列表，应该为空
        request = MockMvcRequestBuilders.get("/demo/users");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string(equalTo("[]")));

        // 2、post 提交一个user，spingboot 框架会自动对应属性并填充到参数实现类中
        request = MockMvcRequestBuilders.post("/demo/user")
                .param("id", "1")
                .param("name", "lili")
                .param("age", "20")
                .param("password", "20");

        // 2、post 提交一个user
        request = MockMvcRequestBuilders.post("/demo/user")
                .param("id", "1")
                .param("name", "lili")
                .param("age", "20");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 3、get一个id为1的user
        request = MockMvcRequestBuilders.put("/demo/user/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo("{\"id\":1,\"name\":\"lili\",\"age\":20}")));

    }

    @Test
    public void testError() {

        RequestBuilder request = null;
        try {
            request = MockMvcRequestBuilders.post("/demo/saveUser")
                    .param("id", "1")
                    .param("name", "")
                    .param("age", "200")
                    .param("password", "11");
            mvc.perform(request).andDo(MockMvcResultHandlers.print());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * spring mvc测试框架提供了两种方式，独立安装和集成Web环境测试（此种方式并不会集成真正的web环境，而是通过相应的Mock API进行模拟测试，无须启动服务器）

     1、mockMvc.perform执行一个请求；

     2、MockMvcRequestBuilders.get("/user/1")构造一个请求

     3、ResultActions.andExpect添加执行完成后的断言

     4、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情，比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。

     5、ResultActions.andReturn表示执行完成后返回相应的结果。


     MockMvcBuilder是用来构造MockMvc的构造器，其主要有两个实现：StandaloneMockMvcBuilder和DefaultMockMvcBuilder，StandaloneMockMvcBuilder继承了DefaultMockMvcBuilder。直接使用静态工厂MockMvcBuilders创建即可：

     MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；

     MockMvcBuilders.standaloneSetup(Object... controllers)：通过参数指定一组控制器，这样就不需要从上下文获取了；

     其中DefaultMockMvcBuilder还提供了如下API：

     addFilters(Filter... filters)/addFilter(Filter filter, String... urlPatterns)：添加javax.servlet.Filter过滤器

     defaultRequest(RequestBuilder requestBuilder)：默认的RequestBuilder，每次执行时会合并到自定义的RequestBuilder中，即提供公共请求数据的；

     alwaysExpect(ResultMatcher resultMatcher)：定义全局的结果验证器，即每次执行请求时都进行验证的规则；

     alwaysDo(ResultHandler resultHandler)：定义全局结果处理器，即每次请求时都进行结果处理；

     dispatchOptions：DispatcherServlet是否分发OPTIONS请求方法到控制器；



     StandaloneMockMvcBuilder继承了DefaultMockMvcBuilder，又提供了如下API：

     setMessageConverters(HttpMessageConverter<?>...messageConverters)：设置HTTP消息转换器；

     setValidator(Validator validator)：设置验证器；

     setConversionService(FormattingConversionService conversionService)：设置转换服务；

     addInterceptors(HandlerInterceptor... interceptors)/addMappedInterceptors(String[] pathPatterns, HandlerInterceptor... interceptors)：添加spring mvc拦截器；

     setContentNegotiationManager(ContentNegotiationManager contentNegotiationManager)：设置内容协商管理器；

     setAsyncRequestTimeout(long timeout)：设置异步超时时间；

     setCustomArgumentResolvers(HandlerMethodArgumentResolver... argumentResolvers)：设置自定义控制器方法参数解析器；

     setCustomReturnValueHandlers(HandlerMethodReturnValueHandler... handlers)：设置自定义控制器方法返回值处理器；

     setHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers)/setHandlerExceptionResolvers(HandlerExceptionResolver... exceptionResolvers)：设置异常解析器；

     setViewResolvers(ViewResolver...resolvers)：设置视图解析器；

     setSingleView(View view)：设置单个视图，即视图解析时总是解析到这一个（仅适用于只有一个视图的情况）；

     setLocaleResolver(LocaleResolver localeResolver)：设置Local解析器；

     setFlashMapManager(FlashMapManager flashMapManager)：设置FlashMapManager，如存储重定向数据；

     setUseSuffixPatternMatch(boolean useSuffixPatternMatch)：设置是否是后缀模式匹配，如“/user”是否匹配"/user.*"，默认真即匹配；

     setUseTrailingSlashPatternMatch(boolean useTrailingSlashPatternMatch)：设置是否自动后缀路径模式匹配，如“/user”是否匹配“/user/”，默认真即匹配；

     addPlaceHolderValue(String name, String value) ：添加request mapping中的占位符替代；

     因为StandaloneMockMvcBuilder不会加载Spring MVC配置文件，因此就不会注册我们需要的一些组件，因此就提供了如上API用于注册我们需要的相应组件。



     MockMvcRequestBuilders主要API：

     MockHttpServletRequestBuilder get(String urlTemplate, Object... urlVariables)：根据uri模板和uri变量值得到一个GET请求方式的MockHttpServletRequestBuilder；如get("/user/{id}", 1L)；

     MockHttpServletRequestBuilder post(String urlTemplate, Object... urlVariables)：同get类似，但是是POST方法；

     MockHttpServletRequestBuilder put(String urlTemplate, Object... urlVariables)：同get类似，但是是PUT方法；

     MockHttpServletRequestBuilder delete(String urlTemplate, Object... urlVariables) ：同get类似，但是是DELETE方法；

     MockHttpServletRequestBuilder options(String urlTemplate, Object... urlVariables)：同get类似，但是是OPTIONS方法；




     ResultActions（在请求得到响应之后，就会自动生成）：

     ResultActions：

     调用MockMvc.perform(RequestBuilder requestBuilder)后将得到ResultActions，通过ResultActions完成如下三件事：

     ResultActions andExpect(ResultMatcher matcher) ：添加验证断言来判断执行请求后的结果是否是预期的；

     ResultActions andDo(ResultHandler handler) ：添加结果处理器，用于对验证成功后执行的动作，如输出请求及结果过程信息用于调试；

     MvcResult andReturn() ：返回验证成功后的MvcResult；用于自定义验证/下一步的异步处理；(主要是拿到结果进一步做自定义断言)
     */
}
