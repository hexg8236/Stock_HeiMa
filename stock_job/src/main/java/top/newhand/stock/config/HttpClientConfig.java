package top.newhand.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName HttpClientConfig
 * @Author HeXianGang
 * @Date 2024/2/18 17:44
 * @Version 1.0
 * @Description 定义访问Http服务的配置类
 **/

@Configuration
public class HttpClientConfig {

    /**
     * 定义restTemplate bean
     * @return
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
