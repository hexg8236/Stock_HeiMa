package top.newhand.stock.service;

import top.newhand.stock.pojo.domain.InnerMarketDomain;
import top.newhand.stock.pojo.domain.StockBlockDomain;
import top.newhand.stock.pojo.domain.StockUpdownListDomain;
import top.newhand.stock.vo.R;
import top.newhand.stock.vo.resp.PageResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StockService
 * @Author HeXianGang
 * @Date 2024/2/18 12:42
 * @Version 1.0
 * @Description
 **/
public interface StockService {

    /**
     * @Description 获取国内大盘的实时数据
     * @Param []
     * @Date 12:45 2024/2/18
     **/
    R<List<InnerMarketDomain>> innerIndexAll();

    /**
     * @Description 获取部门信息
     * @Param []
     * @Date 14:24 2024/2/18
     **/
    R<List<StockBlockDomain>> sectorAllLimit();
    
    /**
     * @Description 获取股票涨幅信息
     * @Param [page, pageSize]
     * @Date 14:24 2024/2/18
     **/
    R<PageResult> getStockPageInfo(Integer page, Integer pageSize);

    /**
     * @Description 获取涨幅榜信息
     * @Param []
     * @Date 14:46 2024/2/18
     **/
    R<List<StockUpdownListDomain>> getStockUpdownList();

    /**
     * @Description 统计最新交易日下股票每分钟涨停的数量
     * @Param []
     * @Date 16:21 2024/2/18
     **/
    R<Map> getStockUpdownCount();

    /**
     * @Description 将指定页的股票数据导出到excel表下
     * @Param [response, page, pageSize]
     * @Date 16:54 2024/2/18
     **/
    void stockExport(HttpServletResponse response, Integer page, Integer pageSize) throws IOException;

    R<List<InnerMarketDomain>> getNewestInnerMarketInfos();
}
