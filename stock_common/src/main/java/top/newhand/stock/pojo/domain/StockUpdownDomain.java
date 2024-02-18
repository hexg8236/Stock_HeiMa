package top.newhand.stock.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName StockUpdownDomain
 * @Author HeXianGang
 * @Date 2024/2/18 14:12
 * @Version 1.0
 * @Description 股票涨跌信息
 **/
@Data
public class StockUpdownDomain {
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
     * @Description 交易量
     * @Param 
     * @Date 14:44 2024/2/18
     **/
    private Long tradeAmt;

    /**
     * @Description 交易金额
     * @Param 
     * @Date 14:44 2024/2/18
     **/
    private BigDecimal tradeVol;

    /**
     * 日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date curDate;
}
