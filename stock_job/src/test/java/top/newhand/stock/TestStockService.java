package top.newhand.stock;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.newhand.stock.service.StockTimerService;

/**
 * @ClassName TestStockService
 * @Author HeXianGang
 * @Date 2024/2/19 15:51
 * @Version 1.0
 * @Description 测试StockTimerService
 **/

@SpringBootTest
@MapperScan("top.newhand.stock.mapper")
public class TestStockService {

    @Autowired
    private StockTimerService stockTimerService;


    @Test
    public void testGetStockRtIndex() {
        stockTimerService.getStockRtIndex();
    }

}
