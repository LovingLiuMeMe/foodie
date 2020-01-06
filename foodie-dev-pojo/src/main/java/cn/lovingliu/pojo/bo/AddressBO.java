package cn.lovingliu.pojo.bo;

import cn.lovingliu.common.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Author：LovingLiu
 * @Description: 收货地址的BO
 * @Date：Created in 2020-01-04
 */
@Data
public class AddressBO {
    private String addressId;
    private String userId;
    @Length(max = 12,min = 1,message = "收货人姓名必须填")
    private String receiver;
    @IsMobile
    private String mobile;
    @NotBlank(message = "省级未设置")
    private String province;
    @NotBlank(message = "城市未设置")
    private String city;
    @NotBlank(message = "区域请设置")
    private String district;
    @NotBlank(message = "详情地址请设置")
    private String detail;
}
