package cn.lovingliu.pojo.vo;

import lombok.Data;

/**
 * @Author：LovingLiu
 * @Description: 三级分类VO
 * @Date：Created in 2020-01-02
 */
@Data
public class SubCategoryVO {
    private Integer subId;
    private String subName;
    private Integer subType;
    private Integer subFatherId;
}
