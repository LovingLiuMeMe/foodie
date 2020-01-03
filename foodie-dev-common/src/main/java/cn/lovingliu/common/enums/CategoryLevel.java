package cn.lovingliu.common.enums;

/**
 * @Author：LovingLiu
 * @Description: 性别枚举
 * @Date：Created in 2019-12-30
 */
public enum CategoryLevel {
    LEVEL1(1,"第一级");

    public final Integer type;
    public final String value;

    CategoryLevel(int type, String value){
        this.type = type;
        this.value = value;
    }

}
