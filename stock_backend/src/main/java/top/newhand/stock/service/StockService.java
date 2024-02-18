package top.newhand.stock.service;

import top.newhand.stock.pojo.domain.InnerMarketDomain;
import top.newhand.stock.pojo.domain.StockBlockDomain;
import top.newhand.stock.vo.R;

import java.util.List;

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

    R<List<StockBlockDomain>> sectorAllLimit();
}
