package top.newhand.stock.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.newhand.stock.pojo.domain.InnerMarketDomain;
import top.newhand.stock.pojo.domain.StockBlockDomain;
import top.newhand.stock.pojo.domain.StockUpdownListDomain;
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

}
