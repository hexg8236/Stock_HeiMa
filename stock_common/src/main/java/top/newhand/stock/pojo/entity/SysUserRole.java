package top.newhand.stock.pojo.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户角色表
 * @TableName sys_user_role
 */
@Data
public class SysUserRole implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}