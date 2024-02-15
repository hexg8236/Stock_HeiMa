package top.newhand;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @ClassName TestRedis
 * @Author HeXianGang
 * @Date 2024/2/15 15:32
 * @Version 1.0
 * @Description 测试Redis连接
 **/

@SpringBootTest
public class TestRedis {
    /**
     * @Description RedisTemplate
     * @Param
     * @Date 15:33 2024/2/15
     **/
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis() {
        // 存入数据
        redisTemplate.opsForValue().set("myname", "zhangsan");
        // 获取数据
        String myname = (String)redisTemplate.opsForValue().get("myname");
        System.out.println(myname);
    }
}
