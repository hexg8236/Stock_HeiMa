package top.newhand.stock.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import top.newhand.stock.mapper.StockBusinessMapper;
import top.newhand.stock.service.StockTimerService;

import java.util.List;

/**
 * @ClassName StockTimerService
 * @Author HeXianGang
 * @Date 2024/2/19 15:12
 * @Version 1.0
 * @Description 股票数据采集服务类接口
 **/

@Service
@Slf4j
public class StockTimerServiceImpl implements StockTimerService {

    /**
     * @Description 查询A股票代码的数据接口
     * @Param
     * @Date 15:00 2024/2/19
     **/
    @Autowired
    private StockBusinessMapper stockBusinessMapper;


    /**
     * @Description 获取股票所有CODE
     * @Param []
     * @Date 15:13 2024/2/19
     **/
    @Override
    public List<String> getStockIds() {
        return stockBusinessMapper.getStockIds();    
    }




}
