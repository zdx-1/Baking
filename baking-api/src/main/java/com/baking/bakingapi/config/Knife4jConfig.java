package com.baking.bakingapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@Component
@EnableSwagger2WebMvc
public class Knife4jConfig {
    //配置Swagger2的Docket的Bean实例
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // apiInfo()：配置 API 的一些基本信息，比如：文档标题title，文档描述description，文档版本号version
                .apiInfo(apiInfo())
                // select()：生成 API 文档的选择器，用于指定要生成哪些 API 文档
                .select()
                // apis()：指定要生成哪个包下的 API 文档
                .apis(RequestHandlerSelectors.basePackage("com.baking.config.controller"))
                // paths()：指定要生成哪个 URL 匹配模式下的 API 文档。这里使用 PathSelectors.any()，表示生成所有的 API 文档。
                .paths(PathSelectors.any())
                .build();
    }

    //文档信息配置
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title("烘焙项目")
                // 文档描述信息
                .description("烘焙项目在线API文档")
                // 文档版本号
                .version("1.0")
                .build();
    }
}