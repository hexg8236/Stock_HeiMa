package top.newhand.stock.job;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.newhand.stock.service.StockTimerTaskService;

/**
 * @ClassName StockJob
 * @Author HeXianGang
 * @Date 2024/2/20 18:08
 * @Version 1.0
 * @Description 定义股票相关的数据的定时任务
 **/
@Component
public class StockJob {

    @Autowired
    private StockTimerTaskService stockTimerTaskService;

    @XxlJob("hello_job")
    public void testJob() {
        System.out.println("testStartJobRun");
    }


    /**
     * 定义定时任务，采集国内大盘数据
     */
    @XxlJob("RunStockInnerMarketInfos")
    public void RunStockInnerMarketInfos(){
        stockTimerTaskService.getInnerMarketInfo();
    }

    @XxlJob("RunStockOuterMarketInfos")
    public void RunStockOuterMarketInfos(){
        stockTimerTaskService.getOuterMarketInfo();
    }

    @XxlJob("RunStockRtIndex")
    public void RunStockRtIndex(){
        stockTimerTaskService.getStockRtIndex();
    }

    @XxlJob("RunStockBlockRtIndex")
    public void RunStockBlockRtIndex(){
        stockTimerTaskService.getStockBlockRtIndex();
    }
}
