package top.newhand.stock;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.newhand.stock.service.StockTimerService;
import top.newhand.stock.service.StockTimerTaskService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestStockTimerService
 * @Author HeXianGang
 * @Date 2024/2/18 21:11
 * @Version 1.0
 * @Description 测试股票采集服务
 **/

@SpringBootTest
@MapperScan("top.newhand.stock.mapper")
public class TestStockTimerService {


    @Autowired
    private StockTimerTaskService stockTimerTaskService;

    @Autowired
    private StockTimerService stockTimerService;


    /**
     * @Description 测试股票采集服务
     * @Param []
     * @Date 21:13 2024/2/18
     **/
    @Test
    public void testGetInnerMarketInfo() {
        stockTimerTaskService.getInnerMarketInfo();
    }

    @Test
    public void testGetOuterMarketInfo() {
        stockTimerTaskService.getOuterMarketInfo();
    }
    @Test
    public void testGetStockIds() {
        List<String> stockIds = stockTimerService.getStockIds();
        System.out.println(stockIds);
    }

    @Test
    public void testPortion() {
        List<Integer> all=new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            all.add(i);
        }
        //将集合均等分，每份大小最多15个
        Lists.partition(all,15).forEach(ids->{
            System.out.println(ids);
        });
    }

    @Test
    public void testGetStockRtIndex() {
        stockTimerTaskService.getStockRtIndex();
    }

    @Test
    public void testGetStockBlockRtIndex() {
        stockTimerTaskService.getStockBlockRtIndex();
    }



}
