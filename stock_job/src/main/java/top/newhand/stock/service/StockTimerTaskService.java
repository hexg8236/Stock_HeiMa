package top.newhand.stock.service;

/**
 * @ClassName StockTimerTaskService
 * @Author HeXianGang
 * @Date 2024/2/18 20:59
 * @Version 1.0
 * @Description 定义采集股票数据的定时任务的服务接口
 **/

public interface StockTimerTaskService {
    
    /**
     * @Description 获取国内大盘的实时数量信息
     * @Param []
     * @Date 21:04 2024/2/18
     **/
    void getInnerMarketInfo();

    /**
     * @Description 获取国外大盘的实时信息
     * @Param []
     * @Date 19:03 2024/2/20
     **/
    void getOuterMarketInfo();

    /**
     * @Description 获取A股的个票实时数据
     * @Param []
     * @Date 19:00 2024/2/20
     **/
    void getStockRtIndex();

    /**
     * @Description 获取股票板块数据
     * @Param []
     * @Date 10:33 2024/2/20
     **/
    void getStockBlockRtIndex();

}
