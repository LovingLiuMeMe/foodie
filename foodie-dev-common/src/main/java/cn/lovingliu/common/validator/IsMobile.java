package cn.lovingliu.common.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author：LovingLiu
 * @Description: 自定义校验逻辑
 * @Date：Created in 2020-01-04
 */
@Target({ElementType.FIELD,ElementType.METHOD}) // 可以添加在什么类型上
@Retention(RetentionPolicy.RUNTIME) // 起作用的时机
@Constraint(validatedBy = IsMobileValidator.class) // 实现的逻辑（要执行的校验逻辑）
public @interface IsMobile {
    String message() default "手机号格式不正确";

    boolean nullable() default false;

    boolean blankable() default false;

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
