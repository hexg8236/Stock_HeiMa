package top.newhand.stock.service.impl;

import com.alibaba.excel.EasyExcel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.newhand.stock.mapper.StockBlockRtInfoMapper;
import top.newhand.stock.mapper.StockMarketIndexInfoMapper;
import top.newhand.stock.mapper.StockRtInfoMapper;
import top.newhand.stock.pojo.domain.InnerMarketDomain;
import top.newhand.stock.pojo.domain.StockBlockDomain;
import top.newhand.stock.pojo.domain.StockUpdownDomain;
import top.newhand.stock.pojo.domain.StockUpdownListDomain;
import top.newhand.stock.pojo.vo.StockInfoConfig;
import top.newhand.stock.service.StockService;
import top.newhand.stock.utils.DateTimeUtil;
import top.newhand.stock.vo.R;
import top.newhand.stock.vo.ResponseCode;
import top.newhand.stock.vo.resp.PageResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StockServiceImpl
 * @Author HeXianGang
 * @Date 2024/2/18 12:42
 * @Version 1.0
 * @Description 大盘数据服务
 **/
@Service
@Slf4j
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
     * @Description 股票涨跌信息数据接口
     * @Param
     * @Date 14:25 2024/2/18
     **/
    @Autowired
    private StockRtInfoMapper stockRtInfoMapper;

    /**
     * @Description 股票板块数据接口
     * @Param
     * @Date 13:44 2024/2/18
     **/
    @Autowired
    private StockBlockRtInfoMapper stockBlockRtInfoMapper;

    @Autowired
    private Cache<String, Object> caffeineCache;

    @Override
    public R<List<InnerMarketDomain>> innerIndexAll() {
        // 1、获取国内A股的大盘集合
        List<String> inners = stockInfoConfig.getInner();
        // 2、获取最近股票交易日期
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //TODO mock测试数据，后期数据通过第三方接口动态获取实时数据 可删除
//        Date lastDate = DateTime.parse(lastDate1, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        // 3、将获取的Java Date传入接口
        List<InnerMarketDomain> marketInfo = stockMarketIndexInfoMapper.getMarketInfo(inners, lastDate);
        return R.ok(marketInfo);
    }

    /**
     * @Description 查询板块指数服务
     * @Param []
     * @Date 14:29 2024/2/18
     **/
    @Override
    public R<List<StockBlockDomain>> sectorAllLimit() {
        //获取股票最新交易时间点
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //TODO mock数据,后续删除
//        lastDate = DateTime.parse("2021-12-21 14:30:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        // 1、调用mapper接口获取数据
        List<StockBlockDomain> stockBlockDomains = stockBlockRtInfoMapper.sectorAll(lastDate);
        if (CollectionUtils.isEmpty(stockBlockDomains)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA.getMessage());
        }
        return R.ok(stockBlockDomains);
    }

    @Override
    public R<PageResult> getStockPageInfo(Integer page, Integer pageSize) {
        // 1、 设置PageHelper 分页参数
        PageHelper.startPage(page, pageSize);
        // 2、根据当前最新的股票交易时间地点
        Date curDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        // TODO mock数据
//        curDate = DateTime.parse("2022-06-07 15:00:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        // 3、调用Mapper接口查询
        List<StockUpdownDomain> infos = stockRtInfoMapper.getNewestStockInfo(curDate);
        // 判断查询数据是否有响应值
        if (CollectionUtils.isEmpty(infos)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA);
        }
        //4.组装PageInfo对象，获取分页的具体信息,因为PageInfo包含了丰富的分页信息，而部分分页信息是前端不需要的
        //PageInfo<StockUpdownDomain> pageInfo = new PageInfo<>(infos);
        //PageResult<StockUpdownDomain> pageResult = new PageResult<>(pageInfo);
        PageResult<StockUpdownDomain> pageResult = new PageResult<>(new PageInfo<>(infos));
        return R.ok(pageResult);
    }

    /**
     * @Description 查询涨幅榜信息
     * @Param []
     * @Date 14:52 2024/2/18
     **/
    @Override
    public R<List<StockUpdownListDomain>> getStockUpdownList() {
        //获取股票最新交易时间点
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //TODO mock数据,后续删除
//        lastDate = DateTime.parse("2021-12-30 10:30:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        List<StockUpdownListDomain> infos = stockRtInfoMapper.getStockUpdownList(lastDate);
        if (CollectionUtils.isEmpty(infos)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA.getMessage());
        }
        return R.ok(infos);
    }

    /**
     * @Description 统计最新交易日下股票每分钟涨跌停的数量
     * @Param []
     * @Date 16:20 2024/2/18
     **/
    @Override
    public R<Map> getStockUpdownCount() {
        //1.获取最新的交易时间范围 openTime  curTime
        //1.1 获取最新股票交易时间点
        DateTime curDateTime = DateTimeUtil.getLastDate4Stock(DateTime.now());
        Date curTime = curDateTime.toDate();
        //TODO mock 数据
//        curTime = DateTime.parse("2022-01-06 14:25:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //1.2 获取最新交易时间对应的开盘时间
        DateTime openDate = DateTimeUtil.getOpenDate(curDateTime);
        Date openTime = openDate.toDate();
        //TODO mock数据
//        openTime = DateTime.parse("2022-01-06 09:30:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        //2.查询涨停数据
        //约定mapper中flag入参： 1-》涨停数据 0：跌停
        List<Map> upCounts = stockRtInfoMapper.getStockUpdownCount(openTime, curTime, 1);
        //3.查询跌停数据
        List<Map> dwCounts = stockRtInfoMapper.getStockUpdownCount(openTime, curTime, 0);
        //4.组装数据
        HashMap<String, List> mapInfo = new HashMap<>();
        mapInfo.put("upList", upCounts);
        mapInfo.put("downList", dwCounts);
        //5.返回结果
        return R.ok(mapInfo);
    }

    @Override
    public void stockExport(HttpServletResponse response, Integer page, Integer pageSize) throws IOException {
        //1.获取分页数据
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
        //TODO 伪造数据，后续删除
//        lastDate = DateTime.parse("2022-07-07 14:43:00", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).toDate();
        PageHelper.startPage(page, pageSize);
        List<StockUpdownDomain> infos = stockRtInfoMapper.getAllStockUpDownByTime(lastDate);
        response.setCharacterEncoding("utf-8");
        try {
            //2.判断分页数据是否为空，为空则响应json格式的提示信息
            if (CollectionUtils.isEmpty(infos)) {
                R<Object> error = R.error(ResponseCode.NO_RESPONSE_DATA);
                //将error转化成json格式字符串
                String jsonData = new ObjectMapper().writeValueAsString(error);
                //设置响应的数据格式 告知浏览器传入的数据格式
                response.setContentType("application/json");
                //设置编码格式
                //            response.setCharacterEncoding("utf-8");
                //响应数据
                response.getWriter().write(jsonData);
                return;
            }
            //3.调动EasyExcel数据导出
            // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.ms-excel");
//        response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("股票涨幅数据表格", "UTF-8");
            //指定excel导出时默认的文件名称，说白了就是告诉浏览器下载文件时默认的名称为：股票涨幅数据表格
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), StockUpdownDomain.class).sheet("股票涨幅信息").doWrite(infos);
        } catch (Exception e) {
            log.error("导出时间：{},当初页码：{}，导出数据量：{}，发生异常信息：{}", lastDate, page, pageSize, e.getMessage());
        }
    }

    @Override
    public R<List<InnerMarketDomain>> getNewestInnerMarketInfos() {
        return null;
    }

}



