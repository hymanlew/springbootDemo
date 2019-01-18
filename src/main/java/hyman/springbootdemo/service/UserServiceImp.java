package hyman.springbootdemo.service;

import hyman.springbootdemo.dao.UserDao;
import hyman.springbootdemo.dao.UserDaoAnotation;
import hyman.springbootdemo.entity.PropSet;
import hyman.springbootdemo.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Resource(name = "userDaoAnotaion")
    private UserDaoAnotation userDaoAnotation;

    /**
     * CacheManager 管理着多个 cache组件，对缓存的真正 CRUD操作是在 cache组件中，每一个组件都有自己唯一的名字：
     *
     * cacheNames/value：指定缓存组件的名字，可以在 spring 配置文件中定义，必须指定至少一个。
     * key：缓存数据使用的 key，可以为空，也可以指定。指定时要按照 SpEL 表达式编写，如果不指定，默认是使用方法参数的值。
     * keyGenerator：key 的生成器，也可以自定义指定生成器组件（MyCacheConfig）。
     * cacheManager：可指定缓存管理器来获取指定的缓存组件（或者指定 cacheResolver 缓存解析器）。
     * condition：设置符合条件的情况下才缓存，也是按照 SpEL 表达式编写。
     * unless：排除（不缓存），当其指定的条件为 true 时，方法的返回值就不会缓存。可以对方法值的结果进行判断。
     * sync：是否使用异步模式，但要注意，如果使用异步（true），则 unless 就不支持了。
     *
     *
     * @Cacheable 的 key可以设定为 SpEL表达式：
     * 名字		        位置	            描述			        示例
     * methodName	    root object    当前被调用的方法名	    #root.methodName
     * method		    root object    当前被调用的方法		#root.method.name
     * target		    root object    当前被调用的目标对象	#root.target
     * targetClass	    root object    当前被调用的目标对象类	    #root.targetClass
     * args		        root object    当前被调用的方法的参数列表	#root.args[0]
     * caches           root object，  当前方法调用使用的缓存列表（如@Cacheable(value={"cache1", "cache2"})），则有两个cache，#root.caches[0].name
     * argument name    evaluation context  方法参数的名字（可以直接用 #参数名，也可以用 #p0或#a0 的形式，0代表参数的索引）。	#id = #a0 = #p0 = #root.args[0]
     * result           evaluation context  方法执行后的返回值（仅当方法执行之后的判断有效，如‘unless’，’cache put’的表达式 ’cache evict’的表达式beforeInvocation=false）	#result
     *
     * 注意 key 与 keyGenerator，cacheManager 与 cacheResolver，都是只能二选一，不能同时指定，因为其功能都是相同的。
     */

    //@Cacheable(cacheNames = {"emp"})
    //@Cacheable(cacheNames = "emp",keyGenerator = "mykeyGenerator")
    //@Cacheable(cacheNames = "emp",key = "#root.methodName+'['+#id+']'")
    @Cacheable(cacheNames = "emp",key = "#id",condition = "#id>0 and #root.methodName eq 'getById'",unless = "#id == 1")
    //@Cacheable(cacheNames = "emp",key = "#id",condition = "#id>0",unless = "#result == null")
    @Override
    public User getById(Integer id) {
        return userDaoAnotation.getOne(id);
    }

    @Override
    public List<User> findAll() {
        List<User> list = userDaoAnotation.findAll();
        return list;
    }

    @Override
    public List<User> findByName(String name) {
        return null;
    }


    @Resource
    private UserDao userDao;

    //@Override
    //public List<User> findAll() {
    //    return userDao.findAll();
    //}
    //
    //@Override
    //public User getById(Integer id) {
    //    return userDao.getById(id);
    //}
    //
    //@Override
    //public List<User> findByName(String name) {
    //    return userDao.findByName(name);
    //}

    @Override
    public List<User> selectAndCount(Integer count, Integer size) {
        PropSet propSet = new PropSet();
        propSet.setObj1(count);
        propSet.setObj2(size);
        List<User> list = userDao.selectAndCount(propSet);
        return list;
    }
}
