package hyman.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * 传统方式
 *
 */
@RestController
public class ArticleController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/article/list")
    public Set<String> articleList() {
        return redisTemplate.keys("*");
    }

    @GetMapping("/article/save")
    public void articleSave() {
        redisTemplate.opsForValue().set("x", "123");
    }
}
