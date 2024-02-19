package top.newhand.stock.pojo.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockUpdownDomain {
    /**
     * @Description 股票代码
     * @Param
     * @Date 14:41 2024/2/18
     **/
    @ExcelProperty(value = {"股票涨幅信息统计表", "股票编码"}, index = 0)
    private String code;

    /**
     * @Description 股票名称
     * @Param
     * @Date 14:41 2024/2/18
     **/
    @ExcelProperty(value = {"股票涨幅信息统计表", "股票名称"}, index = 1)
    private String name;

    /**
     * @Description 前收盘价
     * @Param
     * @Date 14:41 2024/2/18
     **/
    @ExcelProperty(value = {"股票涨幅信息统计表", "前收盘价格"}, index = 2)
    private BigDecimal preClosePrice;

    /**
     * @Description 当前价格
     * @Param
     * @Date 14:42 2024/2/18
     **/
    @ExcelProperty(value = {"股票涨幅信息统计表", "当前价格"}, index = 3)
    private BigDecimal tradePrice;

    /**
     * @Description 涨跌
     * @Param
     * @Date 14:42 2024/2/18
     **/
    @ExcelProperty(value = {"股票涨幅信息统计表", "涨幅"}, index = 4)
    private BigDecimal increase;

    /**
     * @Description 涨幅
     * @Param
     * @Date 14:42 2024/2/18
     **/
    @ExcelProperty(value = {"股票涨幅信息统计表", "涨跌"}, index = 5)
    private BigDecimal upDown;

    /**
     * @Description 振幅
     * @Param
     * @Date 14:42 2024/2/18
     **/
    @ExcelProperty(value = {"股票涨幅信息统计表", "振幅"}, index = 6)
    private BigDecimal amplitude;


    /**
     * @Description 交易量
     * @Param 
     * @Date 14:44 2024/2/18
     **/
    @ExcelProperty(value = {"股票涨幅信息统计表", "交易总量"}, index = 7)
    private Long tradeAmt;

    /**
     * @Description 交易金额
     * @Param 
     * @Date 14:44 2024/2/18
     **/
    @ExcelProperty(value = {"股票涨幅信息统计表", "交易金额"}, index = 8)
    private BigDecimal tradeVol;

    /**
     * 日期
     */
    @ExcelProperty(value = {"股票涨幅信息统计表","日期"},index = 9)
    //easyExcel的注解-》excel
    @DateTimeFormat("yyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date curDate;
}
