package com.rixinclass.backend.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "用户登录请求参数")
public class LoginDto {
    @NotBlank(message = "工号不能为空")
    @Schema(description = "工号")
    private String workNo;

    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码")
    private String password;
}