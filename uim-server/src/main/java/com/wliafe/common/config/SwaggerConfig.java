package com.wliafe.common.config;

import com.wliafe.common.service.UimService;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/**
 * swagger文档配置文件
 *
 * @author wliafe
 * @date 2023/1/4 19:40
 **/
@Configuration
@EnableWebMvc
public class SwaggerConfig {
    @Autowired
    private UimService uimService;

    private static final String basePackage = "com.wliafe.admin.controller";//需要扫描api路径
    private static final String headerName = "token";//请求头名称

    @Bean
    public OpenAPI customOpenAPI() {
        Components components = new Components();
        //添加右上角的统一安全认证
        components.addSecuritySchemes(headerName,
                new SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .scheme("bearer")
                        .name(headerName)
                        .in(SecurityScheme.In.HEADER)
                        .description("请求头")
        );

        return new OpenAPI()
                .components(components)
                .info(apiInfo());
    }

    @Bean
    public GroupedOpenApi defaultGroup() {
        return GroupedOpenApi.builder()
                .group("default")
                .addOperationCustomizer((operation, handlerMethod) -> {
                    operation.addSecurityItem(new SecurityRequirement().addList(headerName));
                    return operation;
                })
                .packagesToScan(basePackage)
                .build();
    }

    private Info apiInfo() {
        Contact contact = new Contact();
        contact.setName(uimService.getName());
        contact.setUrl(uimService.getUrl());
        contact.setEmail(uimService.getEmail());
        return new Info()
                .title(uimService.getTitle())
                .version(uimService.getVersion())
                .contact(contact)
                .description(uimService.getDescription())
                .license(new License().name("Apache 2.0").url("https://springdoc.org"));
    }
}
