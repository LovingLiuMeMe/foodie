package cn.lovingliu.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 最新商品VO
 * @Date：Created in 2020-01-02
 */
@Data
public class NewItemsVO {
    private Integer rootCatId;
    private String rootCateName;
    private String slogan;
    private String catImage;
    private String bgColor;

    private List<SimpleItemVO> simpleItemList;
}
