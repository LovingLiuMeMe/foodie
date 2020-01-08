package cn.lovingliu.controller.center;

import cn.lovingliu.common.ServerResponse;
import cn.lovingliu.pojo.Users;
import cn.lovingliu.service.center.CenterUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：LovingLiu
 * @Description: 用户中心Controller
 * @Date：Created in 2020-01-06
 */
@Api(value = "center - 用户中心",tags = "用户中心展示的相关接口")
@RestController
@RequestMapping("/center")
public class CenterController {
    @Autowired
    private CenterUserService centerUserService;

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息",httpMethod = "GET")
    @GetMapping("/userInfo")
    public ServerResponse userInfo(@RequestParam String userId){
        Users users = centerUserService.queryUserInfo(userId);
        return ServerResponse.createBySuccess(users);
    }
}
