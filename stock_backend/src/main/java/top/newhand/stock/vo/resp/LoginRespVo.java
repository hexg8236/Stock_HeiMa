package top.newhand.stock.vo.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName LoginRespVo
 * @Author HeXianGang
 * @Date 2024/2/15 6:54
 * @Version 1.0
 * @Description 登录后响应前端的Vo
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRespVo {

    /**
     * @Description 用户ID
     * @Param
     * @Date 6:56 2024/2/15
     **/
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    
    /**
     * @Description 用户电话
     * @Param 
     * @Date 6:57 2024/2/15
     **/
    private String phone;
    /**
     * @Description 用户名
     * @Param
     * @Date 6:58 2024/2/15
     **/
    private String username;

    /**
     * @Description 昵称
     * @Param
     * @Date 6:58 2024/2/15
     **/
    private String nickName;
}
