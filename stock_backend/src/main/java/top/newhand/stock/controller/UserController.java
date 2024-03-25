package top.newhand.stock.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.newhand.stock.pojo.domain.SysUserDomain;
import top.newhand.stock.pojo.domain.UserReqDomain;
import top.newhand.stock.pojo.entity.SysRole;
import top.newhand.stock.service.UserService;
import top.newhand.stock.vo.R;
import top.newhand.stock.vo.req.LoginReqVo;
import top.newhand.stock.vo.resp.LoginRespVo;
import top.newhand.stock.vo.resp.PageResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Author HeXianGang
 * @Date 2024/2/4 16:26
 * @Version 1.0
 * @Description 定义用户处理接口
 **/
@RestController
@RequestMapping("/api")
@Api(value = "用户认证相关接口",tags = "用户功能-用户登录功能")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * @Description 用户登录
     * @Param [vo]
     * @Date 16:09 2024/2/15
     **/
    @PostMapping("/login")
    @ApiOperation(value = "用户登录功能",notes = "用户登录",response = R.class)
    public R<LoginRespVo> login(@RequestBody LoginReqVo vo){
        return userService.login(vo);
    }

    /**
     * @Description 生成登录校验码访问接口
     * @Param []
     * @Date 16:10 2024/2/15
     **/
    @GetMapping("/captcha")
    @ApiOperation(value = "获取验证码功能", notes = "登录界面获取验证码", response = R.class)
    public R<Map> getCaptchaCode() {
        return userService.getCaptchaCode();
    }

    @PostMapping("/users")
    @ApiOperation(value = "查询用户列表", notes = "查询用户列表", response = R.class)
    public R<PageResult<SysUserDomain>> getUsers( @RequestBody UserReqDomain userReqDomain){
       return userService.getUsers(userReqDomain.getPageNum(), userReqDomain.getPageSize(), userReqDomain.getUsername(), userReqDomain.getNickName(), userReqDomain.getStartTime(), userReqDomain.getEndTime());
    }

    @PostMapping("/user")
    @ApiOperation(value = "新增用户", notes = "添加新用户", response = R.class)
    public R addUser( @RequestBody SysUserDomain userDomain){
        return userService.addUser(userDomain);
    }

    @GetMapping("/user/roles/{userid}")
    @ApiOperation(value = "查询用户角色", notes = "查询用户角色", response = R.class)
    public R<Map<String, List>> getRoles(@PathVariable("userid") String userid) {
        Map<String, List> rolesList = new HashMap<>();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("123");
        rolesList.put("ownRoleIds", strings);
        ArrayList<SysRole> sysUsers = new ArrayList<>();
        SysRole role = new SysRole();
        role.setId(123L);
        role.setName("超级管理员");
        role.setDescription("超级管理员");
        sysUsers.add(role);
        rolesList.put("allRole", sysUsers);
        return R.ok(rolesList);
    }
    
}
