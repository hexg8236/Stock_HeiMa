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
    
    /**
     * @Description 大盘参数获取Url
     * @Param 
     * @Date 20:40 2024/2/18
     **/
    private String marketUrl;
    
    /**
     * @Description 板块参数获取URL
     * @Param 
     * @Date 21:04 2024/2/18
     **/
    private String blockUrl;
    
    /**
     * @Description 个股股票区间
     * @Param 
     * @Date 20:59 2024/2/22
     **/
    private List<String> upDownRange;
}
