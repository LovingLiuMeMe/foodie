package cn.lovingliu.pojo.vo;

import lombok.Data;

/**
 * @Author：LovingLiu
 * @Description: 购物车VO
 * @Date：Created in 2020-01-03
 */
@Data
public class ShopcartVO {
    private String itemId;
	private String itemImgUrl;
	private String itemName;
    private String specId;
    private String specName;
    private Integer priceDiscount;
    private Integer priceNormal;
}
