package hyman.springbootdemo;

import hyman.springbootdemo.controller.DemoController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.is;

/**
 * 单独测试 走 mock
 * 测试手册
 * https://docs.spring.io/spring-boot/docs/2.1.5.RELEASE/reference/htmlsingle/#boot-features-testing
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DemoController.class)
public class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testArticleList() throws Exception {
        // mockMvc 模拟发起请求
        this.mockMvc.perform(get("/demo/users")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                // 断言 (状态码)
                .andExpect(status().isOk())
                // 断言 (Json)
                .andExpect(jsonPath("$[0].id", is(0)));
    }
}