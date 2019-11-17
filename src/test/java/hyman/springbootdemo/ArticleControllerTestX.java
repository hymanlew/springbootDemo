package hyman.springbootdemo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hyman.springbootdemo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 单独测试 走端口 server
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticleControllerTestX {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testArticleList() throws IOException {
        String body = this.restTemplate.getForObject("/demo/users", String.class);
        List<User> articleList = new ObjectMapper().readValue(body,new TypeReference<List<User>>() {});
        assertThat(articleList.get(0).getId()).isEqualTo(0L);
    }
}