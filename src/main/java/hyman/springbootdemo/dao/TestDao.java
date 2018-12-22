package hyman.springbootdemo.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * 做为在 service中调用的 dao层，但实际工作的是其父类或是父接口
 * 这样是为了体现高可用（或者是适配器模式）
 */
@Component("dao")
public class TestDao extends DemoDao<Object>{

    /*
     * 如果父级全是 interface接口类型，而且接口实现也是继承关系时，即 service接口及其实现，dao接口及其实现，otherdao接口
     * 及其实现全部是实现的继承关系，则在调用总接口的公共方法时，特别要注意记得，设置父类 dao接口指向当前 dao接口，否则会
     * 无法调用顶级父类的公共方法。
     * 即适配器模式。
     */

    //@Resource(name="sqlSessionTemplate")
    private transient SqlSessionTemplate sqlSessionTemplate;

}
