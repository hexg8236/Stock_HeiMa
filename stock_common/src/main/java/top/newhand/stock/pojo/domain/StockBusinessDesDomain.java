package top.newhand.stock.pojo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName StockBusinessDesDomain
 * @Author HeXianGang
 * @Date 2024/2/22 22:19
 * @Version 1.0
 * @Description 个股主营业务实体类
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockBusinessDesDomain {

    // 股票编码
    private String code;
    //公司名称
    private String name;
    //行业，也就是行业板块名称
    private String trade;
    //公司主营业务
    private String business;

}
