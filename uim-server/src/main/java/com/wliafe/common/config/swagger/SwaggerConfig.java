package com.wliafe.common.config.swagger;

import com.wliafe.common.config.my.UimConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Autowired
    private UimConfig uimConfig;
    @Value("${swagger.enabled}")
    private boolean enabled;
    @Value("${swagger.controller-path}")
    private String controllerPath;

    @Bean
    public Docket createRustApi() {
        return new Docket(DocumentationType.OAS_30)
                .enable(enabled)
                .apiInfo(apiInfo())
                .select()
                // 扫描所有有注解的api，用这种方式更灵活
                //  .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描指定包中的swagger注解
                .apis(RequestHandlerSelectors.basePackage(controllerPath))
                // 扫描所有
                //  .apis(RequestHandlerSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置标题
                .title(uimConfig.getTitle())
                // 描述
                .description(uimConfig.getDescription())
                // 作者信息
                .contact(new Contact(uimConfig.getAuthor(), null, null))
                // 版本
                .version("版本号:" + uimConfig.getVersion())
                .build();
    }
}
