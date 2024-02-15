package top.newhand;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName TestPasswordEncoder
 * @Author HeXianGang
 * @Date 2024/2/15 7:04
 * @Version 1.0
 * @Description 测试密码加密匹配器
 **/

@SpringBootTest
public class TestPasswordEncoder {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testPasswordEncoder() {
        String pwd = "123456";
        for (int i = 0; i < 10; i ++) {
            String encoderPwd = passwordEncoder.encode(pwd);
            System.out.println(encoderPwd);
        }
    }

    /**
     * @Description 测试匹配
     * 底层原理： 从密文中获取盐值（随件码，参与密文生成的运算）后，利用盐值与密文密码进行加密的得到密文
     * 这个密文与输入的密文等值匹配
     * @Param []
     * @Date 7:08 2024/2/15
     **/
    @Test
    public void testDecode() {
        String encodePwd="$2a$10$cYAFZmq2w.rPCax63gfqkenOjVKZbVq4BEkURa1pGDQqkS.PHcW/G";
        String pwd="123456";
        boolean isSuccess = passwordEncoder.matches(pwd, encodePwd);
        System.out.println(isSuccess?"匹配成功":"匹配失败");
    }
}
