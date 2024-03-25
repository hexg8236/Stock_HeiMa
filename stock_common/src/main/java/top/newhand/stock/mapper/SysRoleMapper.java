package top.newhand.stock.mapper;

import top.newhand.stock.pojo.entity.SysRole;

import java.util.List;

/**
* @author hexg8
* @description 针对表【sys_role(角色表)】的数据库操作Mapper
* @createDate 2024-02-02 21:35:16
* @Entity top.newhand.stock.pojo.entity.SysRole
*/
public interface SysRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /**
     * @description TODO 查询角色列表根据条件
     * @date 2024/3/23 11:29
     * @param record
     * @return java.util.List<top.newhand.stock.pojo.entity.SysRole>
     */
    List<SysRole> selectRolesByUserSysRole(SysRole record);

}
