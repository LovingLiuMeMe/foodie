package cn.lovingliu.pojo.bo;

import lombok.Data;

/**
 * @Author：LovingLiu
 * @Description: 购物车业务逻辑相关
 * @Date：Created in 2020-01-03
 */
@Data
public class ShopcartBO {
    private String itemId;
	private String itemImgUrl;
	private String itemName;
    private String specId;
    private String specName;
    private Integer buyCounts;
    private Integer priceDiscount;
    private Integer priceNormal;
}
