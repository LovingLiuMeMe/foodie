package cn.lovingliu.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author：LovingLiu
 * @Description: BO 接收前端所传的参数
 * @Date：Created in 2019-12-29
 */
@Data
@ApiModel(value = "用户对象BO",description = "从客户端,由用户传入的数据封装在此entity中")
public class UserBO {
    @ApiModelProperty(value = "用户名" ,name = "用户名",example = "admin",required = true)
    private String username;
    @ApiModelProperty(value = "密码" ,name = "密码",example = "123456",required = true)
    private String password;
    @ApiModelProperty(value = "确认密码" ,name = "确认密码",example = "123456",required = false)
    private String confirmPassword;
}
