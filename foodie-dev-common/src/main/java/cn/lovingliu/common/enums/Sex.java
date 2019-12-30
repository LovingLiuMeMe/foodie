package cn.lovingliu.common.enums;

/**
 * @Author：LovingLiu
 * @Description: 性别枚举
 * @Date：Created in 2019-12-30
 */
public enum Sex {
    WOMAN(0,"女"),
    MAN(1,"男"),
    SECRET(2,"保密");

    public final Integer type;
    public final String value;

    Sex(int type, String value){
        this.type = type;
        this.value = value;
    }

}
