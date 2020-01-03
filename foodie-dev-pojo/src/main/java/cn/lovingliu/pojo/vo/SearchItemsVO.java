package cn.lovingliu.pojo.vo;

import lombok.Data;

/**
 * @Author：LovingLiu
 * @Description: 用于展示商品搜索列表结果的VO
 * @Date：Created in 2020-01-02
 */
@Data
public class SearchItemsVO {
    private String itemId;
    private String itemName;
    private Integer sellCounts;
    private String imgUrl;
    private Integer price;
}
