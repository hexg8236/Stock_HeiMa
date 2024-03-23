package top.newhand.stock.service;

import top.newhand.stock.pojo.domain.SysUserDomain;
import top.newhand.stock.pojo.entity.SysUser;
import top.newhand.stock.vo.R;
import top.newhand.stock.vo.req.LoginReqVo;
import top.newhand.stock.vo.resp.LoginRespVo;
import top.newhand.stock.vo.resp.PageResult;
import top.newhand.stock.vo.resp.RolesRespVo;

import java.util.Map;


/**
 * ClassName UserService
 * Author hexg8
 * Date 2024/2/4 16:09
 * Version 1.0
 * Description 定义用户服务接口
 **/
public interface UserService {

    /**
     * Description 根据用户名称查找用户信息
     * Param [userName]
     * Date 16:22 2024/2/4
     **/
    SysUser getUserByUserName(String userName);

    R<LoginRespVo> login(LoginReqVo vo);

    R<Map> getCaptchaCode();

    R<PageResult<SysUserDomain>> getUsers(Integer page, Integer pageSize, String username, String nickName, String startTime, String endTime);

    R addUser(SysUserDomain userReqDomain);

    R<RolesRespVo> getRolesList(String userid);
}
