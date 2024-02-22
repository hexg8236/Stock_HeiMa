package top.newhand.stock.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.newhand.stock.pojo.domain.*;
import top.newhand.stock.service.StockService;
import top.newhand.stock.vo.R;
import top.newhand.stock.vo.resp.PageResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StockController
 * @Author HeXianGang
 * @Date 2024/2/18 12:39
 * @Version 1.0
 * @Description 大盘数据接口Controller
 **/

@Api(value = "大盘数据接口", tags = "首页-国内指数功能")
@RestController
@RequestMapping("/api/quot")
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * @Description 获取国内最新大盘数据
     * @Param []
     * @Date 12:45 2024/2/18
     **/
    @GetMapping("/index/all")
    public R<List<InnerMarketDomain>> innerIndexAll() {
        return stockService.innerIndexAll();
    }

    /**
     * @Description 获取板块信息
     * @Param []
     * @Date 14:20 2024/2/18
     **/
    @GetMapping("/sector/all")
    public R<List<StockBlockDomain>> sectorAll() {
        return stockService.sectorAllLimit();
    }

    /**
     * @Description 获取股票涨跌信息
     * @Param [page, pageSize]
     * @Date 14:34 2024/2/18
     **/
    @GetMapping("/stock/all")
    public R<PageResult> getStockPageInfo(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                                          @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {

        return stockService.getStockPageInfo(page, pageSize);
    }

    /**
     * @Description 获取涨幅榜信息
     * @Param []
     * @Date 14:46 2024/2/18
     **/
    @GetMapping("/stock/increase")
    public R<List<StockUpdownListDomain>> getStockUpdownList() {
        return stockService.getStockUpdownList();
    }

    /**
     * @Description 统计最新交易日下股票费仲涨停的数量
     * @Param []
     * @Date 16:19 2024/2/18
     **/
    @GetMapping("/stock/updown/count")
    public R<Map> getStockUpdownCount() {
        return stockService.getStockUpdownCount();
    }


    /**
     * @Description 将指定页的股票数据导出到Excel下
     * @Param [response, page, pageSize]
     * @Date 16:53 2024/2/18
     **/
    @GetMapping("/stock/export")
    public void stockExport(HttpServletResponse response, Integer page, Integer pageSize) throws IOException {
        stockService.stockExport(response,page,pageSize);
    }

    /**
     * @Description 成交量对比功能
     * @Param []
     * @Date 20:32 2024/2/22
     **/
    @GetMapping("/stock/tradeAmt")
    public R<Map> stockTradeVol4InnerMarket(){
        return stockService.stockTradeVol4InnerMarket();
    }

    /**
     * 查询当前时间下股票的涨跌幅度区间统计功能
     * 如果当前日期不在有效时间内，则以最近的一个股票交易时间作为查询点
     * @return
     */
    @GetMapping("/stock/updown")
    public R<Map> getStockUpDown() {
        return stockService.stockUpDownScopeCount();
    }

    /**
     * 功能描述：查询单个个股的分时行情数据，也就是统计指定股票T日每分钟的交易数据；
     *         如果当前日期不在有效时间内，则以最近的一个股票交易时间作为查询时间点
     * @param code 股票编码
     * @return
     */
    @GetMapping("/stock/screen/time-sharing")
    public R<List<Stock4MinuteDomain>> stockScreenTimeSharing(String code){
        return stockService.stockScreenTimeSharing(code);
    }
    
    /**
     * @Description 单个个股K， 数据查询， 可以根据时间时区查询数日的K线数据
     * @Param [stockCode]
     * @Date 21:32 2024/2/22
     **/
    @GetMapping("/stock/screen/dkline")
    public R<List<Stock4EvrDayDomain>> getDayKLinData(@RequestParam("code") String stockCode) {
        return stockService.sotckCreenDkLine(stockCode);
    }

    /**
     * @Description 获取外盘指数行情查询
     * @Param []
     * @Date 21:51 2024/2/22
     **/
    @GetMapping("/external/index")
    public R<List<OuterMarketDomain>> getExternalIndex() {
        return stockService.outerIndexAll();
    }

    /**
     * @Description 股票模糊查询
     * @Param [searchStr]
     * @Date 22:14 2024/2/22
     **/
    @GetMapping("/stock/search")
    public R<List<Map<String, String>>> stockCodeSearch(@RequestParam("searchStr") String searchStr){
        return stockService.searchCode(searchStr);
    }

    /**
     * @Description 获取个股主营业查询
     * @Param [stockCode]
     * @Date 22:31 2024/2/22
     **/
    @GetMapping("/stock/describe")
    public R<StockBusinessDesDomain> getStockBusinessDes(@RequestParam("code") String stockCode) {
        return stockService.getStockBusinessDes(stockCode);
    }
}
