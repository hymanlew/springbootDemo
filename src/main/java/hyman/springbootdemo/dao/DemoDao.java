package hyman.springbootdemo.dao;

import hyman.springbootdemo.entity.PropSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("supperdao")
public interface DemoDao<T> {

    /**
     * Spring的 JdbcTemplate是自动配置的，你可以直接使用 @Autowired 来注入到你自己的bean中来使用。是底层方法。
     * 它是一种基本的数据访问方式（底层），结合构建 RESTful API 和使用 Thymeleaf 模板引擎渲染 Web视图的内容就已经可以完成
     * App服务端和 Web站点的开发任务了。
     *
     * @Resource
     * private JdbcTemplate jdbcTemplate;
     */

    /*
     * 如果父级全是 interface接口类型，而且接口实现也是继承关系时，即 service接口及其实现，dao接口及其实现，otherdao接口
     * 及其实现全部是实现的继承关系，则在调用总接口的公共方法时，特别要注意记得，设置父类 dao接口指向当前 dao接口，否则会
     * 无法调用顶级父类的公共方法。
     * 即适配器模式。
     *
     * @Resource(name="sqlSessionTemplate")
     * private transient SqlSessionTemplate sqlSessionTemplate;
     */

    T getById(Integer id);

    List<T> findAll();

    List<T> selectAndCount(PropSet propSet);
}
