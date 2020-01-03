package cn.lovingliu.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 二级分类的VO
 * @Date：Created in 2020-01-02
 */
@Data
public class CategoryVO {
    private Integer id;
    private String name;
    private Integer type;
    private Integer fatherId;

    // 三级分类VO
    private List<SubCategoryVO> subCatList;
}
