package cn.lovingliu.pojo.vo;

import lombok.Data;

/**
 * @Author：LovingLiu
 * @Description: 6个最细商品的简单信息
 * @Date：Created in 2020-01-02
 */
@Data
public class SimpleItemVO {
    private String itemId;
    private String itemName;
    private String itemUrl;
    private String createdTime;
}
