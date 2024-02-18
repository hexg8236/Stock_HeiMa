package top.newhand.stock.service.impl;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.newhand.stock.mapper.StockBlockRtInfoMapper;
import top.newhand.stock.mapper.StockMarketIndexInfoMapper;
import top.newhand.stock.pojo.domain.InnerMarketDomain;
import top.newhand.stock.pojo.domain.StockBlockDomain;
import top.newhand.stock.pojo.vo.StockInfoConfig;
import top.newhand.stock.service.StockService;
import top.newhand.stock.utils.DateTimeUtil;
import top.newhand.stock.vo.R;
import top.newhand.stock.vo.ResponseCode;

import java.util.Date;
import java.util.List;

/**
 * @ClassName StockServiceImpl
 * @Author HeXianGang
 * @Date 2024/2/18 12:42
 * @Version 1.0
 * @Description 大盘数据服务
 **/
@Service
public class StockServiceImpl implements StockService {

    /**
     * @Description 大盘数据配置类
     * @Param 
     * @Date 12:49 2024/2/18
     **/
    @Autowired
    private StockInfoConfig stockInfoConfig;
    /**
     * @Description 股票市场信息数据接口
     * @Param 
     * @Date 12:49 2024/2/18
     **/    
    @Autowired
    private StockMarketIndexInfoMapper stockMarketIndexInfoMapper;

    /**
     * @Description 股票板块数据接口
     * @Param
     * @Date 13:44 2024/2/18
     **/
    @Autowired
    private StockBlockRtInfoMapper stockBlockRtInfoMapper;
    @Override
    public R<List<InnerMarketDomain>> innerIndexAll() {
        // 1、获取国内A股的大盘集合
        List<String> inners = stockInfoConfig.getInner();
        // 2、获取最近股票交易日期
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //TODO mock测试数据，后期数据通过第三方接口动态获取实时数据 可删除
        lastDate=DateTime.parse("2022-01-02 09:32:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        // 3、将获取的Java Date传入接口
        List<InnerMarketDomain> marketInfo = stockMarketIndexInfoMapper.getMarketInfo(inners, lastDate);
        return R.ok(marketInfo);
    }

    @Override
    public R<List<StockBlockDomain>> sectorAllLimit() {
        //获取股票最新交易时间点
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //TODO mock数据,后续删除
        lastDate=DateTime.parse("2021-12-21 14:30:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        // 1、调用mapper接口获取数据
        List<StockBlockDomain> stockBlockDomains = stockBlockRtInfoMapper.sectorAll(lastDate);
        if (CollectionUtils.isEmpty(stockBlockDomains)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA.getMessage());
        }
        return R.ok(stockBlockDomains);
    }
}
