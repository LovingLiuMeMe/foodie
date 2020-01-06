package cn.lovingliu.pojo.bo;

import lombok.Data;

/**
 * @Author：LovingLiu
 * @Description: 创建订单BO
 * @Date：Created in 2020-01-05
 */
@Data
public class SubmitOrderBO {
    private String userId;
    private String itemSpecIds;
    private String addressId;
    private Integer payMethod;
    private String leftMsg;
}
