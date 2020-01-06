package cn.lovingliu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author：LovingLiu
 * @Description: 静态文件加载
 * @Date：Created in 2020-01-05
 */
@Controller
@ApiIgnore
public class TempResultController {

    @GetMapping("/alipayResult")
    public String alipayResult(HttpServletRequest request, HttpServletResponse response) {

        return "alipayResult";
    }
}
