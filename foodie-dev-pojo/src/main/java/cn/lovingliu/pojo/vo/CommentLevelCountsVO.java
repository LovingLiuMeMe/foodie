package cn.lovingliu.pojo.vo;

import lombok.Data;

/**
 * @Author：LovingLiu
 * @Description: 用于展示商品评价数的VO
 * @Date：Created in 2020-01-02
 */
@Data
public class CommentLevelCountsVO {
    public Integer totalCounts;
    public Integer goodCounts;
    public Integer normalCounts;
    public Integer badCounts;
}
