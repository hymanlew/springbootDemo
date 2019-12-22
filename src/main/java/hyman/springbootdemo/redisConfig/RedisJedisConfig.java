//package hyman.springbootdemo.redisConfig;
//
//import lombok.extern.slf4j.Slf4j;
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.CacheErrorHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * @EnableCaching，必须加，使配置生效。
// *
// * Redis 的三个客户端实现：
// * jedis：是 Redis的 Java实现客户端，提供了比较全面的Redis命令的支持。使用阻塞的I/O，其方法调用都是同步的，程序流需要等到 sockets处理完
// *        I/O才能执行，不支持异步。当多线程使用同一个连接时，是线程不安全的。所以要使用连接池，为每个jedis实例分配一个连接。
// *
// * Lettuce：当多线程使用同一连接实例时，是线程安全的。基于Netty框架的事件驱动的通信层，其方法调用是异步的。是高级 Redis客户端，用于线程安
// *        全同步，异步和响应使用，支持集群，Sentinel，管道和编码器。主要在一些分布式缓存框架上使用比较多。
// *
// * Redisson：实现了分布式和可扩展的Java数据结构。基于Netty框架的事件驱动的通信层，其方法调用是异步的。Redisson的API是线程安全的，所以可以
// *        操作单个Redisson连接来完成各种操作。提供很多分布式相关操作服务，例如，分布式锁，分布式集合，可通过Redis支持延迟队列。
// *
// * 对于客户端的选择，尽量遵循各尽其用的原理，尽管 Jedis比起 Redisson有各种各样的不足，但也应该在需要使用 Redisson的高级特性时再选用 Redisson，
// * 避免造成不必要的程序复杂度提升。
// *
// * Jedis 是 Redis官方推荐的面向 Java的操作 Redis的客户端，而 RedisTemplate是 SpringDataRedis 中对 JedisApi 的高度封装。
// * springDataRedis 相对于 Jedis 来说可以方便地更换 Redis的 Java客户端，比 Jedis多了自动管理连接池的特性，方便与其他Spring框架进行搭配使用。如：SpringCache
// */
//@Slf4j
//@Configuration
//@EnableCaching
//public class RedisJedisConfig {
//
//    @Autowired
//    private JedisConnectionFactory jedisConnectionFactory;
//
//
//    @Bean
//    public CacheManager cacheManager() {
//        // 初始化缓存管理器，在这里我们可以缓存的整体过期时间什么的，我这里默认没有配置
//        log.info("初始化 -> [{}]", "CacheManager RedisCacheManager Start");
//        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
//                .RedisCacheManagerBuilder
//                .fromConnectionFactory(jedisConnectionFactory);
//        return builder.build();
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory ) {
//        //设置序列化
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//
//        // 配置redisTemplate
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory);
//        RedisSerializer stringSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(stringSerializer); // key序列化
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer); // value序列化
//        redisTemplate.setHashKeySerializer(stringSerializer); // Hash key序列化
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer); // Hash value序列化
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
//
//    @Bean
//    public CacheErrorHandler errorHandler() {
//        // 异常处理，当Redis发生异常时，打印日志，但是程序正常走
//        log.info("初始化 -> [{}]", "Redis CacheErrorHandler");
//        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
//            @Override
//            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
//                log.error("Redis occur handleCacheGetError：key -> [{}]", key, e);
//            }
//
//            @Override
//            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
//                log.error("Redis occur handleCachePutError：key -> [{}]；value -> [{}]", key, value, e);
//            }
//
//            @Override
//            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key)    {
//                log.error("Redis occur handleCacheEvictError：key -> [{}]", key, e);
//            }
//
//            @Override
//            public void handleCacheClearError(RuntimeException e, Cache cache) {
//                log.error("Redis occur handleCacheClearError：", e);
//            }
//        };
//        return cacheErrorHandler;
//    }
//
//}
