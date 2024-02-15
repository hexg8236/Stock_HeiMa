package top.newhand.stock.mapper;

import top.newhand.stock.pojo.entity.SysRolePermission;

/**
* @author hexg8
* @description 针对表【sys_role_permission(角色权限表)】的数据库操作Mapper
* @createDate 2024-02-02 21:35:16
* @Entity top.newhand.stock.pojo.entity.SysRolePermission
*/
public interface SysRolePermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);

}
