package cn.lovingliu.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author：LovingLiu
 * @Description: Mybatis的配置
 * @Date：Created in 2019-10-29
 */
@Configuration
@MapperScan("cn.lovingliu.mapper")
public class MybatisConfig {

}
