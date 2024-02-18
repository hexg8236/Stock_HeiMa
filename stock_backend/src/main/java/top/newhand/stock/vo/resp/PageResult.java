package top.newhand.stock.vo.resp;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName PageResult
 * @Author HeXianGang
 * @Date 2024/2/18 14:14
 * @Version 1.0
 * @Description 封装分页实体类
 **/

@Data
public class PageResult<T> implements Serializable {
    /**
     * @Description 总记录数
     * @Param
     * @Date 14:15 2024/2/18
     **/
    private Long totalRows;
    /**
     * @Description 总页数
     * @Param 
     * @Date 14:16 2024/2/18
     **/
    private int totalPages;
    /**
     * 当前第几页
     */
    private Integer pageNum;
    /**
     * 每页记录数
     */
    private Integer pageSize;
    /**
     * 当前页记录数
     */
    private Integer size;
    /**
     * 结果集
     */
    private List<T> rows;

    /**
     * 分页数据组装
     * @param pageInfo
     * @return
     */
    public PageResult(PageInfo<T> pageInfo) {
        totalRows = pageInfo.getTotal();
        totalPages = pageInfo.getPages();
        pageNum = pageInfo.getPageNum();
        pageSize = pageInfo.getPageSize();
        size = pageInfo.getSize();
        rows = pageInfo.getList();
    }


}
