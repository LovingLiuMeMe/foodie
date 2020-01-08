package cn.lovingliu;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Author：LovingLiu
 * @Description: SpringBoot web项目的War启动类启动类
 * @Date：Created in 2019-12-29
 */
public class StartWarApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 指向Application这个springBoot启动类
        return builder.sources(Application.class);
    }
}
