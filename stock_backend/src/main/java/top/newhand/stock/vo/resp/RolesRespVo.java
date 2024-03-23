package top.newhand.stock.vo.resp;

import lombok.Data;
import top.newhand.stock.pojo.entity.SysRole;

import java.util.List;

/**
 * @ClassName RolesList
 * @Author HeXianGang
 * @Date 2024/3/21 15:23
 * @Version 1.0
 * @Description 角色队列
 **/

@Data
public class RolesRespVo {

    private List<Long> ownRoleIds;

    private List<SysRole> allRole;
}
