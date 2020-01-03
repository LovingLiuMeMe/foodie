package cn.lovingliu.common.enums;

/**
 * @Author：LovingLiu
 * @Description: 商品评价等级
 * @Date：Created in 2019-12-30
 */
public enum CommentLevel {
    GOOD(1,"好评"),
    NORMAL(2,"中评"),
    BAD(3,"差评");

    public final Integer level;
    public final String value;

    CommentLevel(int level, String value){
        this.level = level;
        this.value = value;
    }

}
