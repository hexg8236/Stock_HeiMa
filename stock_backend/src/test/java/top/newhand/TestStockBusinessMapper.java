package top.newhand;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.newhand.stock.mapper.StockBusinessMapper;

import java.util.List;

/**
 * @ClassName TestStockBuinessMapper
 * @Author HeXianGang
 * @Date 2024/2/19 14:56
 * @Version 1.0
 * @Description 测试mapper
 **/
@SpringBootTest
@MapperScan("top.newhand.stock.mapper")
public class TestStockBusinessMapper {


    @Autowired
    private StockBusinessMapper stockBusinessMapper;

    public void testGetStockIds() {
        List<String> stockIds = stockBusinessMapper.getStockIds();
        System.out.println(stockIds);
    }


}
