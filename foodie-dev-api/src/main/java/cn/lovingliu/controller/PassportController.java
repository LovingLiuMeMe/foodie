package cn.lovingliu.controller;

import cn.lovingliu.pojo.bo.UserBO;
import cn.lovingliu.common.ServerResponse;
import cn.lovingliu.common.util.CookieUtils;
import cn.lovingliu.common.util.JsonUtils;
import cn.lovingliu.pojo.Users;
import cn.lovingliu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author：LovingLiu
 * @Description: Users 相关的Controller
 * @Date：Created in 2019-12-29
 */
@Api(value = "注册登录", tags = {"用于注册登录的相关接口"})
@RestController
@RequestMapping("/passport")
@Slf4j
public class PassportController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在",httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public ServerResponse usernameIsExist(@RequestParam String username) {
        if(StringUtils.isBlank(username)){
            return ServerResponse.createByErrorMessage("用户名不能为空");
        }
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return ServerResponse.createByErrorMessage("用户名已经存在");
        }
        return ServerResponse.createBySuccess();
    }
    @ApiOperation(value = "用户注册",notes = "用户注册",httpMethod = "POST")
    @PostMapping("/regist")
    public ServerResponse regist(@RequestBody UserBO userBO,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPwd = userBO.getConfirmPassword();

        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return ServerResponse.createByErrorMessage("用户名已经存在");
        }

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return ServerResponse.createByErrorMessage("用户名/密码不能为空");
        }
        if (password.length() < 6){
            return ServerResponse.createByErrorMessage("密码的长度不能少于6位");
        }
        if (!password.equals(confirmPwd)) {
            return ServerResponse.createByErrorMessage("两次输入的密码不一致");
        }
        Users usersResult = userService.createUser(userBO);
        usersResult = setNullProperty(usersResult);
        CookieUtils.setCookie(request, response,"user", JsonUtils.objectToJson(usersResult),true);

        // TODO 生成用户token,存入redis会话
        // TODO 同步购物车数据

        return ServerResponse.createBySuccess("注册成功", usersResult);
    }

    @ApiOperation(value = "用户登录",notes = "用户登录",httpMethod = "POST")
    @PostMapping("/login")
    public ServerResponse login(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) {
        String username = userBO.getUsername();
        String password = userBO.getPassword();

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return ServerResponse.createByErrorMessage("用户名/密码不能为空");
        }

        Users usersResult = userService.queryUserForLogin(username, password);
        if (usersResult == null){
            return ServerResponse.createByErrorMessage("用户名或密码不正确");
        }
        usersResult = setNullProperty(usersResult);
        CookieUtils.setCookie(request, response,"user", JsonUtils.objectToJson(usersResult),true);

        // TODO 生成用户token,存入redis会话
        // TODO 同步购物车数据

        return ServerResponse.createBySuccess("登录成功", usersResult);
    }

    @ApiOperation(value = "用户退出",notes = "用户退出",httpMethod = "POST")
    @PostMapping("/logout")
    public ServerResponse logout(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response) {
        // 清除 Cookie
        CookieUtils.deleteCookie(request, response, "user");
        // TODO 用户退出登录 清除购物车信息
        // TODO 分布式会话中需要清除用户数据
        return ServerResponse.createBySuccessMessage("退出登录成功");
    }

    private Users setNullProperty(Users usersResult){
        usersResult.setPassword(null);
        usersResult.setMobile(null);
        usersResult.setEmail(null);
        usersResult.setCreatedTime(null);
        usersResult.setCreatedTime(null);
        usersResult.setBirthday(null);
        return usersResult;
    }
}
