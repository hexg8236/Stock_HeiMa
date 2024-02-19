package top.newhand.stock.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import top.newhand.stock.pojo.vo.StockInfoConfig;
import top.newhand.stock.utils.IdWorker;

/**
 * @ClassName CommonConfig
 * @Author HeXianGang
 * @Date 2024/2/15 7:01
 * @Version 1.0
 * @Description 定义公共配置类
 **/
@Configuration
@EnableConfigurationProperties(StockInfoConfig.class)
public class CommonConfig {

    /**
     * @Description 将BCryptPasswordEncoder注入到Spring容器
     * @Param []
     * @Date 7:03 2024/2/15
     **/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @Description 配置Id生成器Bean
     * @Param []
     * @Date 15:47 2024/2/15
     **/
    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1l, 2l);
    }

    public Cache<String, Object> caffeineCache() {
        return Caffeine.newBuilder()
                // 设置最大缓存数量上限
                .maximumSize(200)
                // 访问1秒后删除
                //.expireAfterAccess(1, TimeUnit.SECONDS)
                //写入1秒后删除
                //.expireAfterWrite(1, TimeUnit.SECONDS)
                //设置初始缓存大小
                .initialCapacity(100)
                // 开启统计
                .recordStats()
                .build();
    }

}
