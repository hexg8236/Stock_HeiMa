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

}
