package cn.lovingliu.common.validator;

import cn.lovingliu.common.util.MobileEmailUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2020-01-04
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile,String> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private boolean nullable;
    private boolean blankable;
    private String message;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            if (!this.nullable) {
                //禁止默认消息返回
                context.disableDefaultConstraintViolation();
                //自定义返回消息
                context.buildConstraintViolationWithTemplate("手机号不能为空").addConstraintViolation();
                return false;
            } else {
                return true;
            }
        } else if (value.length() == 0) {
            if (!this.blankable) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("手机号不能为空白").addConstraintViolation();
                return false;
            } else {
                return true;
            }
        } else if (MobileEmailUtils.checkMobileIsOk(value)) {
            return true;
        } else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate((String) StringUtils.defaultIfBlank(this.message, "手机号格式不对")).addConstraintViolation();
            return false;
        }
    }

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        logger.info("IsMobile init ......");
        this.nullable = constraintAnnotation.nullable();
        this.message = constraintAnnotation.message();
        this.blankable = constraintAnnotation.blankable();
    }
}
