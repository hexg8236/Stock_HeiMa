package top.newhand.stock.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName StockBlockDomain
 * @Author HeXianGang
 * @Date 2024/2/18 13:25
 * @Version 1.0
 * @Description 股票板块Domain
 **/

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class StockBlockDomain {
    /**
     * 公司数量
     */
    private Integer companyNum;
    /**
     * 交易量
     */
    private Long tradeAmt;
    /**
     * 板块编码
     */
    private String code;
    /**
     * 平均价
     */
    private BigDecimal avgPrice;
    /**
     * 板块名称
     */
    private String name;
    /**
     * 当前日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date curDate;
    /**
     *交易金额
     */
    private BigDecimal tradeVol;
    /**
     * 涨跌率
     */
    private BigDecimal updownRate;
}