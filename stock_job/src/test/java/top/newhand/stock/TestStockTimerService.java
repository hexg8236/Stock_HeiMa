package top.newhand.stock;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.newhand.stock.service.StockTimerTaskService;

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


    /**
     * @Description 测试股票采集服务
     * @Param []
     * @Date 21:13 2024/2/18
     **/
    @Test
    public void testGetInnerMarketInfo() {
        stockTimerTaskService.getInnerMarketInfo();
    }
}
