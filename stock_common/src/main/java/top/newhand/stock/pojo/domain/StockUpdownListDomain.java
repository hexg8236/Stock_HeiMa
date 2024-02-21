package top.newhand.stock.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName StockUpdownListDomain
 * @Author HeXianGang
 * @Date 2024/2/18 14:40
 * @Version 1.0
 * @Description 股票涨停榜实体类
 **/
@Data
public class StockUpdownListDomain {
    /**
     * @Description 股票代码
     * @Param 
     * @Date 14:41 2024/2/18
     **/
    private String code;

    /**
     * @Description 股票名称
     * @Param 
     * @Date 14:41 2024/2/18
     **/
    private String name;

    /**
     * @Description 前收盘价
     * @Param 
     * @Date 14:41 2024/2/18
     **/
    private BigDecimal preClosePrice;

    /**
     * @Description 当前价格
     * @Param 
     * @Date 14:42 2024/2/18
     **/
    private BigDecimal tradePrice;
    
    /**
     * @Description 交易金额
     * @Param 
     * @Date 15:06 2024/2/18
     **/
    private BigDecimal tradeVol;

    /**
     * @Description 交易量
     * @Param 
     * @Date 15:07 2024/2/18
     **/
    private Long tradeAmt;

    /**
     * @Description 涨跌
     * @Param 
     * @Date 14:42 2024/2/18
     **/
    private BigDecimal increase;

    /**
     * @Description 涨幅
     * @Param 
     * @Date 14:42 2024/2/18
     **/
    private BigDecimal upDown;

    /**
     * @Description 振幅
     * @Param 
     * @Date 14:42 2024/2/18
     **/
    private BigDecimal amplitude;

    /**
     * @Description 当前时间
     * @Param 
     * @Date 14:43 2024/2/18
     **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date curDate;
}
