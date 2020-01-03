package cn.lovingliu.common.enums;

/**
 * @Author：LovingLiu
 * @Description: 性别枚举
 * @Date：Created in 2019-12-30
 */
public enum YesOrNo {
    NO(0,"否定"),
    YES(1,"确定");

    public final Integer type;
    public final String value;

    YesOrNo(int type, String value){
        this.type = type;
        this.value = value;
    }

}
