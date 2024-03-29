package top.newhand.stock.mapper;

import org.apache.ibatis.annotations.Param;
import top.newhand.stock.pojo.domain.InnerMarketDomain;
import top.newhand.stock.pojo.entity.StockMarketIndexInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author hexg8
* @description 针对表【stock_market_index_info(国内大盘数据详情表)】的数据库操作Mapper
* @createDate 2024-02-02 21:35:16
* @Entity top.newhand.stock.pojo.entity.StockMarketIndexInfo
*/
public interface StockMarketIndexInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockMarketIndexInfo record);

    int insertSelective(StockMarketIndexInfo record);

    StockMarketIndexInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockMarketIndexInfo record);

    int updateByPrimaryKey(StockMarketIndexInfo record);

    /**
     * @Description 获取国内股票信息数据接口
     * @Param [marketIds, timePoint]
     * @Date 14:32 2024/2/18
     **/
    List<InnerMarketDomain> getMarketInfo(@Param("marketIds") List<String> marketIds, @Param("timePoint") Date timePoint);

    /**
     * @Description 批量插入股票大盘信息
     * @Param [list]
     * @Date 21:26 2024/2/18
     **/
    int insertBatch(ArrayList<StockMarketIndexInfo> list);

    /**
     * @Description 根据时间范围和指定的大盘id统计每分钟的交易量
     * @Param [markedIds, startTime4T, endTime4T]
     * @Date 20:35 2024/2/22
     **/
    List<Map> getStockTradeVol(@Param("markedIds") List<String> markedIds, @Param("startTime") Date startTime4T, @Param("endTime") Date endTime4T);
}
