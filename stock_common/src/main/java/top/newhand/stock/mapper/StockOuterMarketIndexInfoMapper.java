package top.newhand.stock.mapper;

import top.newhand.stock.pojo.entity.StockOuterMarketIndexInfo;

import java.util.ArrayList;

/**
* @author hexg8
* @description 针对表【stock_outer_market_index_info(外盘详情信息表)】的数据库操作Mapper
* @createDate 2024-02-02 21:35:16
* @Entity top.newhand.stock.pojo.entity.StockOuterMarketIndexInfo
*/
public interface StockOuterMarketIndexInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockOuterMarketIndexInfo record);

    int insertSelective(StockOuterMarketIndexInfo record);

    StockOuterMarketIndexInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockOuterMarketIndexInfo record);

    int updateByPrimaryKey(StockOuterMarketIndexInfo record);

    /**
     * @Description 批量插入数据接口
     * @Param [list]
     * @Date 21:01 2024/2/20
     **/
    int insertBatch(ArrayList<StockOuterMarketIndexInfo> list);
}
