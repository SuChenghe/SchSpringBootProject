package com.suchenghe.common.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author SuChenghe
 * @date 2019/3/28 16:45
 */
@Configuration
@EnableSwagger2
public class SchSwagger {
    @Bean
    public Docket creatRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiinfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.suchenghe.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiinfo() {
        return new ApiInfoBuilder()
                .title("SchSpringBootProject利用swagger构建的Api文档")
                .description("控制器请求的方法路径")
                .termsOfServiceUrl("")
                .version("1.0").build();
    }
}
