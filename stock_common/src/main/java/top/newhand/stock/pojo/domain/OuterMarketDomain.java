package top.newhand.stock.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName OuterMarketDomain
 * @Author HeXianGang
 * @Date 2024/2/22 21:51
 * @Version 1.0
 * @Description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OuterMarketDomain {


    /**
     * 大盘名称
     */
    private String name;

    /**
     * 当前点
     */
    private BigDecimal curPoint;


    /**
     * 涨跌值
     */
    private BigDecimal upDown;

    /**
     * 涨幅
     */
    private BigDecimal rose;

    /**
     * 当前时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date curTime;

}
