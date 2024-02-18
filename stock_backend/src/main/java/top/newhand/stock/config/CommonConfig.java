package top.newhand.stock.config;

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
}
