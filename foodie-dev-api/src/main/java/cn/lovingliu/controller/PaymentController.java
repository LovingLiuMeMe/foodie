package cn.lovingliu.controller;

import cn.lovingliu.common.ServerResponse;
import cn.lovingliu.common.enums.OrderStatusEnum;
import cn.lovingliu.common.resource.AliPayResource;
import cn.lovingliu.common.util.CurrencyUtils;
import cn.lovingliu.common.util.DateUtil;
import cn.lovingliu.pojo.Orders;
import cn.lovingliu.service.OrdersService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author：LovingLiu
 * @Description: 支付相关逻辑(由于微信支付没有沙箱环境,所以暂时不支持微信支付)
 * @Date：Created in 2020-01-05
 */
@RestController
@RequestMapping(value = "/payment")
@Slf4j
public class PaymentController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private AliPayResource aliPayResource;

    @ResponseBody
    @RequestMapping(value="/goAlipay")
    public ServerResponse<String> goAlipay(@RequestParam String merchantOrderId,
                                           @RequestParam String merchantUserId) throws Exception{

        // 查询订单详情
        Orders waitPayOrder = ordersService.queryOrderByStatus(merchantOrderId, merchantUserId);

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayResource.getGatewayUrl(),
                aliPayResource.getAppId(),
                aliPayResource.getMerchantPrivateKey(),
                "json",
                aliPayResource.getCharset(),
                aliPayResource.getAlipayPublicKey(),
                aliPayResource.getSignType());

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(aliPayResource.getReturnUrl());
        alipayRequest.setNotifyUrl(aliPayResource.getNotifyUrl());

        // 商户订单号, 商户网站订单系统中唯一订单号, 必填
        String out_trade_no = merchantOrderId;
        // 付款金额, 必填 单位元
        String total_amount = CurrencyUtils.changeF2Y(waitPayOrder.getRealPayAmount().toString());
        // 订单名称, 必填
        String subject = "天天吃货-付款用户[" + merchantUserId + "]";
        // 商品描述, 可空, 目前先用订单名称
        String body = subject;

        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "2h";

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String alipayForm = "";
        try {
            alipayForm = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        log.info("支付宝支付 - 前往支付页面, alipayForm: \n{}", alipayForm);

        return ServerResponse.createBySuccess(alipayForm);
    }

    @RequestMapping(value="/alipay/notify")
    public String notify(HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info("支付成功后的支付宝异步通知");

        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params,
                aliPayResource.getAlipayPublicKey(),
                aliPayResource.getCharset(),
                aliPayResource.getSignType()); //调用SDK验证签名

        if(signVerified) {//验证成功
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            // 交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
            // 付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            if (trade_status.equals("TRADE_SUCCESS")){
                // 对订单进行操作
                dealOrderStatus(out_trade_no, OrderStatusEnum.WAIT_DELIVER.type);
            }

            log.info("************* 支付成功(支付宝异步通知) - 时间: {} *************", DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
            log.info("* 订单号: {}", out_trade_no);
            log.info("* 支付宝交易号: {}", trade_no);
            log.info("* 实付金额: {}", total_amount);
            log.info("* 交易状态: {}", trade_status);
            log.info("*****************************************************************************");

            return "success";
        }else {
            //验证失败
            log.info("验签失败, 时间: {}", DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
            return "fail";
        }
    }

    private void dealOrderStatus(String outTradeNo,Integer orderStatus){
        int count = ordersService.updateOrderStatus(outTradeNo, orderStatus);
        if (count > 0){
            log.info("【更定订单付款状态成功】=> {}",outTradeNo);
        }else {
            log.info("【订单已付款,无需再更新】=> {}",outTradeNo);
        }
    }

}
