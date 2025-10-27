package com.rixinclass.backend.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * @Description OpenAPI/Swagger（前端生成 TS SDK 用）
 * @Author DengWenyu
 * @Date 2025/10/27 13:58
 * @Version 1.0
 */

@OpenAPIDefinition(info = @Info(title = "RixinClass API", version = "1.0"))
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@Configuration
public class OpenApiConfig {}
