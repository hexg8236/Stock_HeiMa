package top.newhand.stock.mapper;

import org.apache.ibatis.annotations.Param;
import top.newhand.stock.pojo.domain.StockUpdownDomain;
import top.newhand.stock.pojo.domain.StockUpdownListDomain;
import top.newhand.stock.pojo.entity.StockRtInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author hexg8
* @description 针对表【stock_rt_info(个股详情信息表)】的数据库操作Mapper
* @createDate 2024-02-02 21:35:16
* @Entity top.newhand.stock.pojo.entity.StockRtInfo
*/
public interface StockRtInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockRtInfo record);

    int insertSelective(StockRtInfo record);

    StockRtInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockRtInfo record);

    int updateByPrimaryKey(StockRtInfo record);

    /**
     * @Description 查询指定时间点下股票的数据，并按照涨幅降序排序
     * @Param [curDate]
     * @Date 14:32 2024/2/18
     **/
    List<StockUpdownDomain> getNewestStockInfo(@Param("timePoint") Date curDate);

    /**
     * @Description 查询涨幅榜数据
     * @Param [lastDate]
     * @Date 14:51 2024/2/18
     **/
    List<StockUpdownListDomain> getStockUpdownList(@Param("timePoint") Date lastDate);

    /**
     * @Description 查询指定时间范围内每分钟涨停或者跌停的数量
     * @Param [openTime 开始时间，, curTime结束时间，  flag 约定 1-> 涨停， 0 -> 跌停]
     * @Date 16:22 2024/2/18
     **/
    List<Map> getStockUpdownCount(@Param("openTime") Date openTime, @Param("curTime") Date curTime, @Param("flag") int flag);

    List<StockUpdownDomain> getAllStockUpDownByTime(@Param("timePoint") Date curDate);
}
