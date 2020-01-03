package cn.lovingliu.controller;

import cn.lovingliu.common.ServerResponse;
import cn.lovingliu.pojo.bo.ShopcartBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author：LovingLiu
 * @Description: 购物车Controller
 * @Date：Created in 2020-01-03
 */
@Api(value = "购物车接口Controller",tags = "购物车接口相关API")
@RestController
@RequestMapping("/shopcart")
@Slf4j
public class ShopController {
    @ApiOperation(value = "添加商品并同步商品到购物车",notes = "添加商品并同步商品到购物车",httpMethod = "POST")
    @PostMapping("/add")
    public ServerResponse add(@RequestParam String userId,
                              @RequestBody ShopcartBO shopcartBO,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        if (StringUtils.isBlank(userId)){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        // TODO 前端用户在登录的情况下,添加到商品到购物车,会同时在后端同步购物车到redis缓存
        log.info("ShopcartBO=>{}",shopcartBO);
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "删除商品并同步商品到购物车",notes = "删除商品并同步商品到购物车",httpMethod = "POST")
    @PostMapping("/del")
    public ServerResponse del(@RequestParam String userId,@RequestParam String itemSpecId) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        // TODO 前端用户在登录的情况下,删除购物车中的商品,同步删除相应的redis缓存
        return ServerResponse.createBySuccess();
    }
}
