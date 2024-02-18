package top.newhand.stock.pojo.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @ClassName StockConfig
 * @Author HeXianGang
 * @Date 2024/2/18 11:24
 * @Version 1.0
 * @Description StockInfo配置类
 **/

@ConfigurationProperties(prefix = "stock")
@Data
public class StockInfoConfig {

    /**
     * @Description A股大盘集合
     * @Param 
     * @Date 12:35 2024/2/18
     **/
    private List<String> inner;

    /**
     * @Description 外盘ID集合
     * @Param 
     * @Date 13:24 2024/2/18
     **/
    private List<String> outer;
}
