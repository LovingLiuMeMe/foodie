package cn.lovingliu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author：LovingLiu
 * @Description: SpringBoot 启动类
 * @Date：Created in 2019-12-29
 */
@SpringBootApplication
// 扫描所有包和相关组件包
@ComponentScan(basePackages = {"cn.lovingliu","org.n3r.idworker"})
// 开启定时任务
@EnableScheduling
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
