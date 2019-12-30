package cn.lovingliu.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author：LovingLiu
 * @Description: 对请求的时间进行日志记录
 * @Date：Created in 2019-12-30
 */
@Aspect
@Component
@Slf4j
public class ServiceLogAspect {
    /**
     * AOP通知:
     * 1.前置通知: 在当大调用之前
     * 2.后置通知: 在方法正常调用之后调用
     * 3.环绕通知: 在方法调用之前和之后,都分别可以执行的通知
     * 4.异常通知: 在方法调用过程中发生异常,则通知
     * 5.最终通知: 在方法调用之后执行
     */
    /**
     * 切面表达式
     * 1 .* 代表所有的表达式
     * 2 cn.lovingliu.service.impl 包名
     * 3 .. 代表该包以及其子包下的所有类方法
     * 4 * 代表类名
     * 5 *(..) *代表类中的所有方法, (..) 代表方法中的任何参数
     */
    @Pointcut("execution(* cn.lovingliu.service.impl..*.*(..))")
    public void recordLog(){}

    @Around("recordLog()")
    public Object recordTimeLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("========= 开始执行 {}.{} ==========",
                proceedingJoinPoint.getTarget(),// 类
                proceedingJoinPoint.getSignature().getName());// 方法名
        long begin = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed(); // 执行目标方法
        long end = System.currentTimeMillis();
        long spendTime = end - begin;
        if (spendTime > 6000){
            log.error("========= 执行结束,耗时:{} 毫秒 ==========", spendTime);
        }else if(spendTime > 3000){
            log.warn("========= 执行结束,耗时:{} 毫秒 ==========", spendTime);
        }else {
            log.info("========= 执行结束,耗时:{} 毫秒 ==========", spendTime);
        }
        return result;
    }
}
