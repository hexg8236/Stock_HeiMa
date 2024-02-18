package top.newhand.stock.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.newhand.stock.pojo.domain.InnerMarketDomain;
import top.newhand.stock.pojo.domain.StockBlockDomain;
import top.newhand.stock.service.StockService;
import top.newhand.stock.vo.R;

import java.util.List;

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

    @GetMapping("/sector/all")
    public R<List<StockBlockDomain>> sectorAll() {
        return stockService.sectorAllLimit();
    }




}
