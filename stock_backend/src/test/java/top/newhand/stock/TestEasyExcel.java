package top.newhand.stock;

import com.alibaba.excel.EasyExcel;
import org.junit.jupiter.api.Test;
import top.newhand.stock.pojo.entity.SysUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TestEasyExcel
 * @Author HeXianGang
 * @Date 2024/2/18 16:37
 * @Version 1.0
 * @Description 测试EasyExcel工具
 **/

public class TestEasyExcel {

    public List<SysUser> init(){
        //组装数据
        ArrayList<SysUser> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SysUser user = new SysUser();
            user.setId(123L);
            user.setUsername("张三"+i);
            user.setCreateTime(new Date());
            user.setPhone("10" +i);
            users.add(user);
        }
        return users;
    }
    /**
     * 直接导出后，表头名称默认是实体类中的属性名称
     */
    @Test
    public void test02(){
        List<SysUser> users = init();
        //不做任何注解处理时，表头名称与实体类属性名称一致
        EasyExcel.write("D:\\hexg8\\Desktop\\用户.xls",SysUser.class).sheet("用户信息").doWrite(users);
    }
}
