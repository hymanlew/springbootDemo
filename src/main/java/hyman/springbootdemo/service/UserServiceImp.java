package hyman.springbootdemo.service;

import hyman.springbootdemo.dao.UserDao;
import hyman.springbootdemo.dao.UserDaoAnotation;
import hyman.springbootdemo.entity.PropSet;
import hyman.springbootdemo.entity.User;
import hyman.springbootdemo.util.Logutil;
import hyman.springbootdemo.util.MybatisResults;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 对缓存组件进行统一的设置（公共配置），避免了一个个地设置。
 */
@CacheConfig(cacheNames = "emp")
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
     * @Cacheable 注解是运行在方法执行前，先去查缓存中是否已经有被查询的数据。
     */

    //@Cacheable(cacheNames = {"emp"})
    //@Cacheable(cacheNames = "emp",keyGenerator = "mykeyGenerator")
    //@Cacheable(cacheNames = "emp",key = "#root.methodName+'['+#id+']'")
    //@Cacheable(cacheNames = "emp",key = "#id",condition = "#id>0 and #root.methodName eq 'getById'",unless = "#id == 1")
    @Cacheable(cacheNames = "emp",key = "#id",condition = "#id>0",unless = "#result == null")
    @Override
    public User getById(Integer id) {
        return userDaoAnotation.getOne(id);
    }

    @Override
    public List<User> findAll() {
        List<User> list = userDaoAnotation.findAll();
        return list;
    }

    /**
     * @CachePut，保证方法被调用进行数据修改，并更新缓存数据。该注解是运行在方法执行后，是对结果进行缓存。
     * 一定要注意，它默认的缓存中的 key 是参数对象（即 user），而值就是更新后的 user对象。它与 @Cacheable 中的 key不一样，那么
     * 查询到仍然是更新前的数据（因为查询时进行了缓存）。
     * 所以这里一定要指定 key，并保持与 @Cacheable 的key 相同。
     */
    //@CachePut(cacheNames = "emp",key = "#user.id")
    @CachePut(cacheNames = "emp",key = "#result.id")
    @Override
    public User update(User user) {
        userDaoAnotation.update(user);
        return user;
    }

    /**
     * allEntries（默认为 false）：
     * true 表示删除当前缓存组件中的所有数据。
     * false 表示不删除其他的数据，只删除指定条件的数据。
     *
     * beforeInvocation（默认为 false）：
     * true 表示在方法执行之前清除缓存。
     * false 表示在方法执行之后清除缓存。其目的就是为了防止程序异常，删除失败的情况，从而不清除缓存。
     */
    //@CacheEvict(cacheNames = "emp",allEntries = true)
    @CacheEvict(cacheNames = "emp",key = "#id")
    @Override
    public void delete(Integer id) {
        Logutil.logger.info("=== 已经删除工人："+id);
    }

    /**
     * @Caching 定义了复杂的缓存规则。而且统一的设置可以使用 @CacheConfig 注解进行设置。
     * 由于 CachePut 注解代表了对结果数据的缓存，是在方法执行之后执行的。所以只要是标注了 put注解的方法，则该方法中的 sql 就一
     * 定会被执行的。
     */
    @Caching(
            cacheable = {
                    @Cacheable(key = "#name")
            },
            put = {
                    @CachePut(key = "#result[0].id"),
                    @CachePut(key = "#result[0].age")
            }
    )
    @Override
    public List<User> findByName(String name) {
        return userDaoAnotation.findByName(name);
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
