package top.newhand.stock.mapper;

import top.newhand.stock.pojo.entity.SysUserRole;

/**
* @author hexg8
* @description 针对表【sys_user_role(用户角色表)】的数据库操作Mapper
* @createDate 2024-02-02 21:35:16
* @Entity top.newhand.stock.pojo.entity.SysUserRole
*/
public interface SysUserRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

}
