//package hyman.springbootdemo.dao.imp;
//
//import hyman.springbootdemo.dao.DemoDao;
//import hyman.springbootdemo.entity.PropSet;
//import hyman.springbootdemo.entity.User;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import javax.annotation.Resource;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.List;
//
///**
// * 必须要声明为泛型类型，并且具有继承类，才能向上造型为参数化类型。该类不执行
// * @param <T>
// * @author hyman
// */
////@Repository("supperdao")
//public class DemoDaoImp<T> implements DemoDao<T> {
//
//    /**
//     * SqlSession： 对外提供了用户和数据库之间交互需要的所有方法，隐藏了底层的细节。默认实现类是DefaultSqlSession。
//     * Executor： SqlSession向用户提供操作数据库的方法，但和数据库操作有关的职责都会委托给Executor。
//     *            Executor有若干个实现类，为Executor赋予了不同的能力。
//     *
//     * Cache： MyBatis中的Cache接口，提供了和缓存相关的最基本的操作，如下图所示。
//     *
//     * <setting name="localCacheScope" value="SESSION"/>
//     * 在一级缓存中，其最大的共享范围就是一个SqlSession内部，如果多个SqlSession之间需要共享缓存，则需要使用到二级缓存。开启二级缓存后，
//     * 会使用CachingExecutor装饰Executor，进入一级缓存的查询流程前，先在CachingExecutor进行二级缓存的查询。
//     *
//     * 二级缓存开启后，同一个namespace下的所有操作语句，都影响着同一个Cache，即二级缓存被多个SqlSession共享，是一个全局的变量。
//     * 当开启缓存后，数据的查询执行的流程就是 二级缓存 -> 一级缓存 -> 数据库。
//     */
//
//    /**
//     * MYSQL JDBC快速查询响应的方法，快速返回机制的实现：
//     * 当实现数据导出时，输入查询条件，然后对查询结果进行导出。如果数据量比较大，例如有上亿条记录。之前的解决方案都是多次查询，然后使用limit
//     * 限制每次查询的条数，然后导出。但这样的结果是效率比较低效。而一次查询就把所有结果倒出来，不使用limit分页，则会出现 java.lang.
//     * OutOfMemoryError: 内存溢出问题。
//     *
//     * MySQL在自己的JDBC驱动里提供了特有的功能，来实现查询的快速响应，特别是结果集非常大或者时间较长，而用户非常想尽快看到第一条结果时特别有效。
//     *
//     *   stmt = (com.mysql.jdbc.Statement) con.createStatement();
//     *   stmt.setFetchSize(1);
//     *   //其值为行数，按行读取，打开流方式返回机制
//     *   stmt.enableStreamingResults();
//     *
//     * 对此解释是：MySQL JDBC默认客户端数据接收方式为如下：
//     * 默认为从服务器一次取出所有数据放在客户端内存中，fetch size参数不起作用，当一条SQL返回数据量较大时可能会出现JVM OOM。
//     *
//     * 要一条SQL从服务器读取大量数据，不发生JVM OOM，可以采用以下方法之一：
//     * 1、当statement设置以下属性时，采用的是流数据接收方式，每次只从服务器接收部份数据，直到所有数据处理完毕，不会发生JVM OOM。
//     *
//     *   stmt.setResultSetType(ResultSet.TYPE_FORWARD_ONLY); 只向前滚动
//     *   stmt.setFetchSize(Integer.MIN_VALUE);               其值为行数，按行读取
//     *
//     * 2、调用statement 的 enableStreamingResults方法，实际上 enableStreamingResults方法内部封装的就是第1种方式。
//     *
//     * 3、设置连接属性useCursorFetch=true (5.0版驱动开始支持)，statement以TYPE_FORWARD_ONLY打开，再设置fetch size参数，表示采用
//     *    服务器端游标，每次从服务器取fetch_size条数据。
//     *
//     * 4、使用原生 jdbc连接的分批处理方式，即 executeBatch。
//     *
//     */
//
//    @Resource(name = "sqlSessionTemplate")
//    public transient SqlSessionTemplate sqlSessionTemplate;
//
//    @Resource(name = "jdbcTemplate")
//    public transient JdbcTemplate jdbcTemplate;
//
//    private Class<T> entityClass;
//    private String classname = "";
//
//    public DemoDaoImp(){
//        this.entityClass = null;
//
//        /**
//         * 拿到泛型的类对象：
//         *
//         * Class 类的实例表示正在运行的 Java 应用程序中的类和接口。枚举是一种类，注释是一种接口。每个数组属于被映射为 Class对象的一个类，
//         * 所有具有相同元素类型和维数的数组都共享该 Class对象。基本的 Java类型（boolean、byte、char、short、int、long、float 和
//         * double）和关键字 void 也表示为 Class对象。
//         *
//         * Class 没有公共构造方法。Class 对象是在加载类时由 Java 虚拟机以及通过调用类加载器中的 defineClass 方法自动构造的。
//         */
//        Class c = getClass();
//
//        /**
//         * 类 Object是类层次结构的根类。每个类都使用 Object 作为超类。所有对象（包括数组）都实现这个类的方法。
//         *
//         * Type 是Java 编程语言中所有类型的公共高级接口，也就是Java中所有类型的父接口；它并不是我们平常工作中经常使用的 int、String、List、
//         * Map等数据类型，而是从 Java语言角度来说，对基本类型、引用类型向上的抽象；
//         *
//         * Type体系中的类型包括：原始类型(Class)、参数化类型(ParameterizedType)、数组类型(GenericArrayType)、类型变量(TypeVariable)、
//         * 基本（原生）类型(Class);
//         *
//         * 子接口有 ParameterizedType, TypeVariable（各种类型变量的公共高级接口）, GenericArrayType, WildcardType（表示一个通配符
//         * 类型表达式）, 实现类有Class。
//         *
//         * 原始类型，不仅仅包含我们平常所指的类，还包括枚举、数组、注解等；
//         * 参数化类型，就是我们平常所用到的泛型List、Map，只要是带有泛型的都是参数化类型，即 Class<T>；
//         * 数组类型，并不是我们工作中所使用的数组String[] 、byte[]，而是带有泛型的数组，即T[] ；
//         * 基本类型，也就是我们所说的java的基本类型，即int,float,double等
//         *
//         * 而 Class 类是Type的唯一一个实现类，属于原始类型，是Java反射的基础，是对Java类的抽象，并且是继承自 Object。
//         * 在程序运行期间，每一个类都对应一个Class对象，这个对象包含了类的修饰符、方法，属性、构造等信息，所以我们可以对这个 Class对象进行
//         * 相应的操作，这就是Java的反射；
//         *
//         * 在java 中接口跟类是两个并行的概念，所有类都继承Object，但接口是接口，不继承Object，接口只能继承某一接口，但某个类可以实现接口（多个接口）。
//         * 其次、接口是一系列方法声明的开放集合，即所有实现了该接口的类都具备该接口公开的方法，通常为了隐藏实现以及程序的可拓展性会用到接口，
//         * 因为在所有可以使用接口的地方都可以使用它的实现类来替换（即在运行时编译器会自动调用接口的实现类）。
//         */
//
//        /**
//         * getGenericSuperclass() ：
//         * 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
//         * 如果超类是参数化类型，则返回的 Type 对象必须准确反映源代码中所使用的实际类型参数。如果以前未曾创建表示超类的参数化类型，则创建这个
//         * 类型。有关参数化类型创建过程的语义，请参阅 ParameterizedType 声明。
//         * 如果此 Class表示 Object 类、接口、基本类型或 void，则返回 null。
//         * 如果此对象表示一个数组类，则返回表示 Object 类的 Class 对象。
//         */
//        Type type = c.getGenericSuperclass();
//        if(type instanceof ParameterizedType){
//            /**
//             * getActualTypeArguments() ：
//             * 返回表示此类型实际类型参数的 Type 对象的数组。
//             * 注意，如果此类型表示嵌套在参数化类型中的非参数化类型，则返回的数组为空。
//             */
//            Type[] parameter = ((ParameterizedType) type).getActualTypeArguments();
//
//            // 拿出实例对象
//            this.entityClass = (Class<T>) parameter[0];
//            // 类名是完整路径的名字，需要处理
//            String name = entityClass.getName();
//            this.classname = name.substring(name.lastIndexOf(".")+1);
//        }
//    }
//
//    // 事务处理方式
//    //@Transactional(propagation= Propagation.REQUIRED, isolation= Isolation.READ_COMMITTED)
//
//    @Override
//    public T getById(Integer id){
//        return sqlSessionTemplate.selectOne(this.classname+"Mapper.getById",id);
//    }
//
//    @Override
//    public List<T> findAll() {
//        return sqlSessionTemplate.selectList(this.classname+"Mapper.findAll");
//    }
//
//    @Override
//    public List<T> findByLimit(Integer count, Integer size) {
//        PropSet set = new PropSet();
//        set.setObj1(count);
//        set.setObj2(size);
//        return sqlSessionTemplate.selectList(this.classname+"Mapper.selectAndCount",set);
//    }
//
//    //public T getById2(Object propSet){
//    //    return jdbcTemplate.queryForObject("mapping."+this.classname+"Mapper.find",propSet,User.class);
//    //}
//    //
//    //public T getUser(Object propSet) {
//    //    return sqlSessionTemplate.selectOne("mapping."+this.classname+"Mapper.find",propSet);
//    //}
//
//}
