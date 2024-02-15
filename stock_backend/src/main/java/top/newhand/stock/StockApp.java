package top.newhand.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import top.newhand.stock.config.SwaggerConfiguration;

/**
 * @ClassName StockApp
 * @Author hexg8
 * @Date 2024/2/4 15:43
 * @Version 1.0
 * @Description TODO
 **/
@SpringBootApplication
@MapperScan("top.newhand.stock.mapper")
@Import({SwaggerConfiguration.class})//将swagger配置类导入当前ioc容器中
public class StockApp {
    public static void main(String[] args) {
        SpringApplication.run(StockApp.class, args);
    }
}
