package cn.lovingliu.controller;

import cn.lovingliu.common.ServerResponse;
import cn.lovingliu.common.enums.PayMethod;
import cn.lovingliu.pojo.OrderStatus;
import cn.lovingliu.pojo.bo.SubmitOrderBO;
import cn.lovingliu.service.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author：LovingLiu
 * @Description: 订单Controller
 * @Date：Created in 2020-01-03
 */
@Api(value = "订单相关",tags = "订单相关接口")
@RestController
@RequestMapping("/orders")
@Slf4j
public class OrdersController {
    private static final String FOODIE_SHOPCART = "shopcart";

    @Autowired
    private OrdersService ordersService;

    @ApiOperation(value = "用户下单",notes = "用户下单",httpMethod = "POST")
    @PostMapping("/create")
    public ServerResponse create(@RequestBody SubmitOrderBO submitOrderBO,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        if (submitOrderBO.getPayMethod() != PayMethod.ALIPAY.type
            && submitOrderBO.getPayMethod() != PayMethod.WEIXIN.type) {
            return ServerResponse.createByErrorMessage("支付方式不存在");
        }
        // 1.创建订单
        String orderId = ordersService.createOrder(submitOrderBO);
        // 2.创建订单以后,移除购物车中已结算的商品(已提交)

        // TODO 整合Reids之后,完善购物车中的已结算商品的清除,并且同步到前端的cookie
        // CookieUtils.setCookie(request,response,FOODIE_SHOPCART,"",true); // 清除所有的cookie

        log.info("submitOrderBO=>{}",submitOrderBO);
        return ServerResponse.createBySuccess(orderId);
    }

    @ApiOperation(value = "用户下单",notes = "用户下单",httpMethod = "POST")
    @PostMapping("/getPaidOrderInfo")
    public ServerResponse getPaidOrderInfo(@RequestParam String orderId) {
        OrderStatus orderStatus = ordersService.queryOrderStatusByOrderId(orderId);
        return ServerResponse.createBySuccess(orderStatus);
    }
}
