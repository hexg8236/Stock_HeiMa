package top.newhand.stock.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SwaggerConfiguration
 * @Author HeXianGang
 * @Date 2024/2/15 17:01
 * @Version 1.0
 * @Description Swagger配置类
 **/
@EnableSwagger2
@Configuration
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {
    @Bean
    public Docket buildDocket() {
        //构建在线API概要对象
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .select()
                // 要扫描的API(Controller)基础包
                .apis(RequestHandlerSelectors.basePackage("top.newhand.stock.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo buildApiInfo() {
        //网站联系方式
        Contact contact = new Contact("Newhand","https://newhand.top/","hexg8236@163.com");
        return new ApiInfoBuilder().title("今日指数-在线接口Api文档")
                .description("这是一个方便前后端开发人员快速了解开发接口需求的在线接口API文档")
                .contact(contact)
                .version("1.0.0").build();
    }
}
