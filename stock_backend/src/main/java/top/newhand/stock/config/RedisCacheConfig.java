package top.newhand.stock.config;

import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName RedisCacheConfig
 * @Author HeXianGang
 * @Date 2024/2/15 15:25
 * @Version 1.0
 * @Description Redis配置类
 **/
@Configuration
public class RedisCacheConfig {
    /**
     * @Description 配置redisTemplate bean 自定义序列化方式
     * @Param [redisConnectionFactory] 连接Redis的工厂，底层有场景依赖启动时，自动加载
     * @Date 15:31 2024/2/15
     **/
    @Bean
    public RedisTemplate redisTemplate(@Autowired RedisConnectionFactory redisConnectionFactory) {
        // 1、构建Redis模板对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        // 2、为不同数据结构设置不同的序列化方案
        //设置Key序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        //设置value序列化方式
        template.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        //设置hash中的field字段序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        //设置hash中的value字段序列化方式
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        // 3、初始化参数设置
        template.afterPropertiesSet();
        return template;
    }
}
