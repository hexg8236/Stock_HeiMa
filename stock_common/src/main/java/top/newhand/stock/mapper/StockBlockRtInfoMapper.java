package top.newhand.stock.mapper;

import org.apache.ibatis.annotations.Param;
import top.newhand.stock.pojo.domain.StockBlockDomain;
import top.newhand.stock.pojo.entity.StockBlockRtInfo;

import java.util.Date;
import java.util.List;

/**
* @author hexg8
* @description 针对表【stock_block_rt_info(股票板块详情信息表)】的数据库操作Mapper
* @createDate 2024-02-02 21:35:16
* @Entity top.newhand.stock.pojo.entity.StockBlockRtInfo
*/
public interface StockBlockRtInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockBlockRtInfo record);

    int insertSelective(StockBlockRtInfo record);

    StockBlockRtInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockBlockRtInfo record);

    int updateByPrimaryKey(StockBlockRtInfo record);

    /**
     * @Description 查询板块信息数据接口
     * @Param [lastDate] 最新时间
     * @Date 14:31 2024/2/18
     **/
    List<StockBlockDomain> sectorAll(@Param("timePoint") Date lastDate);
}
