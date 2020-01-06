package cn.lovingliu.service;

import cn.lovingliu.pojo.OrderStatus;
import cn.lovingliu.pojo.Orders;
import cn.lovingliu.pojo.bo.SubmitOrderBO;

public interface OrdersService {
    /**
     * 创建订单相关信息
     * @param submitOrderBO
     * @return
     */
    String createOrder(SubmitOrderBO submitOrderBO);

    /**
     * 根据订单状态查询订单信息
     * @param orderId
     * @param userId
     * @return
     */
    Orders queryOrderByStatus(String orderId,String userId);

    /**
     * 修改订单状态
     * @param orderId
     * @return
     */
    int updateOrderStatus(String orderId, Integer orderStatus);

    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    OrderStatus queryOrderStatusByOrderId(String orderId);

    /**
     * 关闭超时未支付的任务
     * @return
     */
    int closeOrder();
}
