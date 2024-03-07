package top.newhand.stock.pojo.domain;

import lombok.Data;

/**
 * @ClassName SysUser
 * @Author HeXianGang
 * @Date 2024/3/2 20:44
 * @Version 1.0
 * @Description 用户实体类
 **/

@Data
public class SysUserDomain {

    private long id;

    private String username;

    private String password;

    private String  phone;

    private String realName;

    private String nickName;

    private String email;

    private int status;

    private int sex;

    private int deleted;

    private long createId;

    private long updateId;

    private int createWhere;

    private int createTime;

    private int updateTime;
}
