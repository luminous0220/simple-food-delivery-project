package io.github.luminous0220.foodDelivery.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmpLoginDTO implements Serializable {
    @ApiModelProperty(value = "用户名", required = true, example = "admin") // 旧版
    private String username;

    @ApiModelProperty(value = "密码", required = true, example = "admin") // 旧版
    private String password;
}
