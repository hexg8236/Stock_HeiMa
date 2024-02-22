package top.newhand.stock.mapper;

import org.apache.ibatis.annotations.Param;
import top.newhand.stock.pojo.domain.StockBusinessDesDomain;
import top.newhand.stock.pojo.entity.StockBusiness;

import java.util.List;
import java.util.Map;

/**
* @author hexg8
* @description 针对表【stock_business(主营业务表)】的数据库操作Mapper
* @createDate 2024-02-02 21:35:16
* @Entity top.newhand.stock.pojo.entity.StockBusiness
*/
public interface StockBusinessMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StockBusiness record);

    int insertSelective(StockBusiness record);

    StockBusiness selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StockBusiness record);

    int updateByPrimaryKey(StockBusiness record);

    /**
     * @Description 获取所有的股票code
     * @Param []
     * @Date 14:53 2024/2/19
     **/
    List<String> getStockIds();

    /**
     * @Description 根据信息进行模糊查询
     * @Param [searchStr]
     * @Date 22:10 2024/2/22
     **/
    List<Map<String, String>> getStockInfoByBlur(@Param("searchStr") String searchStr);

    /**
     * @Description 获取A股主营业务查询
     * @Param [stockCode]
     * @Date 22:26 2024/2/22
     **/
    StockBusinessDesDomain getBusinessDesByCode(@Param("stockCode") String stockCode);
}
