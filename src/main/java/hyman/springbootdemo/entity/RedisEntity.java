package hyman.springbootdemo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * @EnableRedisRepositories 使用仓储可以实现Redis Hashs与领域对象无缝的转换和存储。
 * 它有一个注释为 org.springframework.data.annotation.Id 的属性id ，其类型注释为 @RedisHash 。这个两个注释构成了持久化该 hash
 * 的真正的key。
 * <p>
 * 有@Id 注解的属性和被命名为id 的属性会被当作标识属性。有注释的属性比其他的更受欢迎。
 */
@Data
@RedisHash("redisEntity")
public class RedisEntity {

    @Id
    String id;
    String firstname;
    String lastname;
    String address;
}
