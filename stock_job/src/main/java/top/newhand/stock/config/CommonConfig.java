package top.newhand.stock.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.newhand.stock.pojo.vo.StockInfoConfig;
import top.newhand.stock.utils.IdWorker;
import top.newhand.stock.utils.ParserStockInfoUtil;

/**
 * @ClassName CommonConfig
 * @Author HeXianGang
 * @Date 2024/2/18 20:35
 * @Version 1.0
 * @Description 公共配置类
 **/
@Configuration
@EnableConfigurationProperties(StockInfoConfig.class)
public class CommonConfig {

    /**
     * 配置基于雪花算法生成全局唯一id
     *   参与元算的参数： 时间戳 + 机房id + 机器id + 序列号
     *   保证id唯一
     * @return
     */
    @Bean
    public IdWorker idWorker () {
        return new IdWorker();
    }

    /**
     * 配置解析工具bean
     * @param idWorker
     * @return
     */
    @Bean
    public ParserStockInfoUtil parserStockInfoUtil(IdWorker idWorker){
        return new ParserStockInfoUtil(idWorker);
    }

}
