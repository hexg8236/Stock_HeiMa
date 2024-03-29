package top.newhand.stock.mapper;

import org.apache.ibatis.annotations.Param;
import top.newhand.stock.pojo.domain.SysUserDomain;
import top.newhand.stock.pojo.entity.SysUser;

import java.util.Date;
import java.util.List;

/**
* @author hexg8
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2024-02-02 21:35:16
* @Entity top.newhand.stock.pojo.entity.SysUser
*/
public interface SysUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser findByUserName(@Param("name") String userName);
    
    /**
     * @Description 获取用户信息根据条件
     * @Param [page, pageSize, username, nickName, startTime, endTime]
     * @Date 21:20 2024/3/2
     **/
    List<SysUserDomain> selectUsers(@Param("page") Integer page, @Param("pageSize") Integer pageSize, @Param("username") String username, @Param("nickName") String nickName, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
