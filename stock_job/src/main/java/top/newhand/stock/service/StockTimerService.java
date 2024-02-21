package top.newhand.stock.service;

import java.util.List;

/**
 * @ClassName StockTimerService
 * @Author HeXianGang
 * @Date 2024/2/19 15:11
 * @Version 1.0
 * @Description 股票采集服务接口
 **/

public interface StockTimerService {

    /**
     * @Description 获取股票ID
     * @Param []
     * @Date 15:12 2024/2/19
     **/
    List<String> getStockIds();



}
