package top.newhand.stock.pojo.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 主营业务表
 * @TableName stock_business
 */
@Data
public class StockBusiness implements Serializable {
    /**
     *  股票编码
     */
    private String stockCode;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 股票所属行业|板块标识
     */
    private String blockLabel;

    /**
     * 行业板块名称
     */
    private String blockName;

    /**
     * 主营业务
     */
    private String business;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}