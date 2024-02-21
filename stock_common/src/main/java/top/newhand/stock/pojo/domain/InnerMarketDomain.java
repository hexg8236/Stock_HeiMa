package top.newhand.stock.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName InnerMarketDomain
 * @Author HeXianGang
 * @Date 2024/2/15 22:44
 * @Version 1.0
 * @Description 定义封装内部大盘数据的实体类
 **/
@Data
public class InnerMarketDomain {

    /**
     * 大盘编码
     */
    private String code;

    /**
     * 大盘名称
     */
    private String name;

    /**
     * 开盘点
     */
    private BigDecimal openPoint;

    /**
     * 当前点
     */
    private BigDecimal curPoint;

    /**
     * 前收盘点
     */
    private BigDecimal preClosePoint;

    /**
     * 交易量
     */
    private Long tradeAmt;

    /**
     * 交易金额
     */
    private Long tradeVol;

    /**
     * 涨跌值
     */
    private BigDecimal upDown;

    /**
     * 涨幅
     */
    private BigDecimal rose;


    /**
     * 振幅
     */
    private BigDecimal amplitude;

    /**
     * 当前时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date curTime;
}
