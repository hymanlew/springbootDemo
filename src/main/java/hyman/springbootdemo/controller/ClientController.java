package hyman.springbootdemo.controller;

import hyman.springbootdemo.entity.Person;
import hyman.springbootdemo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {

    /**
     * http://localhost:8888/client/getById/1
     * http://localhost:8888/client/insertCache/10
     * @param request
     * @return
     */

    @RequestMapping("html")
    public String test(HttpServletRequest request){
        return "error/404";
    }

    @Resource
    private PersonService personService;

    @GetMapping("/getById/{id}")
    public String getById(@PathVariable Integer id) {
        log.info("=== 用 ID 查询 person："+id);

        Person person = personService.getById(id);
        log.info("=== "+person.toString());
        return "getById";
    }

    @Resource
    private CacheManager cacheManager;

    @GetMapping("/insertCache/{id}")
    public String insertCache(@PathVariable Integer id) {
        log.info("=== 将 ID 人员插入缓存："+id);

        Person person = new Person("test",20);
        Cache cache = cacheManager.getCache("person");

        // cacheManager 会自动将缓存组件名称作为对应的 key 值。所以只需要传入 id 即可。
        cache.put(id,person);
        log.info("=== "+person.toString());
        return "insertCache";
    }
}
