package cn.lovingliu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author：LovingLiu
 * @Description: SpringBoot 启动类
 * @Date：Created in 2019-12-29
 */
@SpringBootApplication
// 扫描所有包和相关组件包
@ComponentScan(basePackages = {"cn.lovingliu","org.n3r.idworker"})
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
