package top.newhand.stock.service.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.newhand.stock.mapper.StockBlockRtInfoMapper;
import top.newhand.stock.mapper.StockBusinessMapper;
import top.newhand.stock.mapper.StockRtInfoMapper;
import top.newhand.stock.pojo.entity.StockBlockRtInfo;
import top.newhand.stock.pojo.entity.StockRtInfo;
import top.newhand.stock.pojo.vo.StockInfoConfig;
import top.newhand.stock.service.StockTimerService;
import top.newhand.stock.utils.ParseType;
import top.newhand.stock.utils.ParserStockInfoUtil;

import java.util.List;
import java.util.stream.Collectors;

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

    //注入格式解析bean
    @Autowired
    private ParserStockInfoUtil parserStockInfoUtil;
    
    /**
     * @Description 信息配置类
     * @Param 
     * @Date 15:43 2024/2/19
     **/
    @Autowired
    private StockInfoConfig stockInfoConfig;

    /**
     * @Description 请求客户端bean
     * @Param
     * @Date 15:45 2024/2/19
     **/
    @Autowired
    private RestTemplate restTemplate;

    /**
     * @Description 个股数据接口
     * @Param
     * @Date 15:50 2024/2/19
     **/
    @Autowired
    private StockRtInfoMapper stockRtInfoMapper;

    @Autowired
    private StockBlockRtInfoMapper stockBlockRtInfoMapper;

    /**
     * @Description 获取股票所有CODE
     * @Param []
     * @Date 15:13 2024/2/19
     **/
    @Override
    public List<String> getStockIds() {
        return stockBusinessMapper.getStockIds();    
    }

    /**
     * @Description 获取分钟级股票数据
     * @Param []
     * @Date 15:15 2024/2/19
     **/
    @Override
    public void getStockRtIndex() {
        // 1、批量获取股票ID集合
        List<String> stockIds = stockBusinessMapper.getStockIds();
        // 计算出符合sina命名规范的股票Id数据
        stockIds = stockIds.stream().map(id -> {
            return id.startsWith("6") ? "sh" + id : "sz" + id;
        }).collect(Collectors.toList());
        // 2、设置公共请求对象
        HttpHeaders headers = new HttpHeaders();
        headers.add("Referer","https://finance.sina.com.cn/stock/");
        headers.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        //一次性查询过多，我们将需要查询的数据先进行分片处理，每次最多查询20条股票数据
        Lists.partition(stockIds, 20).forEach(list -> {
            // 拼接Url
            String requestUrl = stockInfoConfig.getMarketUrl() + String.join(",", list);
            String responseBody = restTemplate.postForObject(requestUrl, entity, String.class);
            List<StockRtInfo> infos = parserStockInfoUtil.parser4StockOrMarketInfo(responseBody, ParseType.ASHARE);
            log.info("数据量：{}",infos.size());
            //批量插入数据库
            int i = stockRtInfoMapper.insertBatch(infos);
            log.info("批量插入：{}条", i);
        });

    }

    @Override
    public void getStockBlockRtIndex() {
        // 1、获取请求路径
        String blockUrl = stockInfoConfig.getBlockUrl();
        // 2、设置公共请求对象
        HttpHeaders headers = new HttpHeaders();
        headers.add("Referer","https://finance.sina.com.cn/stock/");
        headers.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String responseBody = restTemplate.postForObject(blockUrl, entity, String.class);
        List<StockBlockRtInfo> stockBlockRtInfos = parserStockInfoUtil.parse4StockBlock(responseBody);
        log.info("数据量：{}",stockBlockRtInfos.size());
        int count = stockBlockRtInfoMapper.insertBatch(stockBlockRtInfos);
        log.info("批量插入：{}条",count);
    }
}
