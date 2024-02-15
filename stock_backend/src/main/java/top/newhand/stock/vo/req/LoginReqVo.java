package top.newhand.stock.vo.req;

import lombok.Data;

/**
 * @ClassName LoginReqVo
 * @Author HeXianGang
 * @Date 2024/2/15 6:52
 * @Version 1.0
 * @Description 登录请求VO
 **/

@Data
public class LoginReqVo {
    /**
     * @Description 用户名
     * @Param
     * @Date 6:53 2024/2/15
     **/
    private String username;

    /**
     * @Description 密码
     * @Param
     * @Date 6:53 2024/2/15
     **/
    private String password;

    /**
     * @Description 验证码
     * @Param
     * @Date 6:53 2024/2/15
     **/
    private String code;
    
    /**
     * @Description 保存redis的随机码key， 就是SessionId
     * @Param 
     * @Date 16:28 2024/2/15
     **/
    private String sessionId;
}
