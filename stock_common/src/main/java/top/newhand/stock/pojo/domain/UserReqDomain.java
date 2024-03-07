package top.newhand.stock.pojo.domain;

import lombok.Data;

/**
 * @ClassName UserReqDomain
 * @Author HeXianGang
 * @Date 2024/3/2 21:50
 * @Version 1.0
 * @Description
 **/

@Data
public class UserReqDomain {

    Integer pageNum;
    Integer pageSize;
    String username;
    String nickName;
    String startTime;
    String endTime;
}


