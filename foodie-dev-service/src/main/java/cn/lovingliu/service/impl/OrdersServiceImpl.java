package cn.lovingliu.service.impl;

import cn.lovingliu.common.enums.OrderStatusEnum;
import cn.lovingliu.common.enums.YesOrNo;
import cn.lovingliu.common.util.DateUtil;
import cn.lovingliu.mapper.OrderItemsMapper;
import cn.lovingliu.mapper.OrderStatusMapper;
import cn.lovingliu.mapper.OrdersMapper;
import cn.lovingliu.pojo.*;
import cn.lovingliu.pojo.bo.SubmitOrderBO;
import cn.lovingliu.service.AddressService;
import cn.lovingliu.service.ItemService;
import cn.lovingliu.service.OrdersService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：LovingLiu
 * @Description: 订单支付
 * @Date：Created in 2019-12-31
 */
@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderStatusMapper orderStatusMapper;
    @Autowired
    private OrderItemsMapper orderItemsMapper;
    @Autowired
    private Sid sid;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ItemService itemService;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String createOrder(SubmitOrderBO submitOrderBO) {
        String userId = submitOrderBO.getUserId();
        String addressId = submitOrderBO.getAddressId();
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        Integer payMethod = submitOrderBO.getPayMethod();
        String leftMsg = submitOrderBO.getLeftMsg();
        Integer postAmount = 0; // 邮费

        // 1.新订单数据保存
        Orders newOrder = new Orders();
        String orderId = sid.nextShort();
        newOrder.setId(orderId);
        newOrder.setUserId(userId);

        UserAddress userAddress = addressService.queryByAddressIdAndUserId(userId,addressId);
        newOrder.setReceiverName(userAddress.getReceiver());
        newOrder.setReceiverMobile(userAddress.getMobile());
        String addressFormat = "%s %s %s %s";

        newOrder.setReceiverAddress(String.format(addressFormat,
                userAddress.getProvince(),
                userAddress.getCity(),
                userAddress.getDistrict(),
                userAddress.getDetail()));
        newOrder.setPayMethod(payMethod);
        newOrder.setPostAmount(postAmount);
        newOrder.setIsComment(YesOrNo.NO.type);
        newOrder.setIsDelete(YesOrNo.NO.type);
        newOrder.setCreatedTime(new Date());
        newOrder.setUpdatedTime(new Date());

        // 2.循环更具ItemSpecIds保存订单商品信息表
        String itemSpecIdArr[] = itemSpecIds.split(",");
        Integer totalAmount = 0;    // 商品原价累计
        Integer realPayAmount = 0;  // 优惠后的实际支付价格累计
        for (String itemSpecId : itemSpecIdArr) {

            // TODO 整合redis后，商品购买的数量重新从redis的购物车中获取
            int buyCounts = 1;

            // 2.1 根据规格id，查询规格的具体信息，主要获取价格
            ItemsSpec itemSpec = itemService.queryItemSpecById(itemSpecId);
            totalAmount += itemSpec.getPriceNormal() * buyCounts;
            realPayAmount += itemSpec.getPriceDiscount() * buyCounts;

            // 2.2 根据商品id，获得商品信息以及商品图片
            String itemId = itemSpec.getItemId();
            Items item = itemService.queryItemById(itemId);
            ItemsImg itemMainImg = itemService.queryItemMainImgById(itemId);

            // 2.3 循环保存子订单数据到数据库
            String subOrderId = sid.nextShort();
            OrderItems subOrderItem = new OrderItems();
            subOrderItem.setId(subOrderId);
            subOrderItem.setOrderId(orderId);
            subOrderItem.setItemId(itemId);
            subOrderItem.setItemName(item.getItemName());
            subOrderItem.setItemImg(itemMainImg.getUrl());
            subOrderItem.setBuyCounts(buyCounts);
            subOrderItem.setItemSpecId(itemSpecId);
            subOrderItem.setItemSpecName(itemSpec.getName());
            subOrderItem.setPrice(itemSpec.getPriceDiscount());
            orderItemsMapper.insertSelective(subOrderItem);

            // 2.4 在用户提交订单以后，规格表中需要扣除库存
            itemService.decreaseItemSpecStock(itemSpecId, buyCounts);
        }

        newOrder.setTotalAmount(totalAmount);
        newOrder.setRealPayAmount(realPayAmount);
        int count = ordersMapper.insert(newOrder);
        // 3.保存订单状态表
        OrderStatus waitPayOrderStatus = new OrderStatus();
        waitPayOrderStatus.setOrderId(orderId);
        waitPayOrderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        waitPayOrderStatus.setCreatedTime(new Date());
        orderStatusMapper.insertSelective(waitPayOrderStatus);

        return orderId;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Orders queryOrderByOrderIdAndUserId(String orderId, String userId) {
        Map<String,Object> parmasMap = new HashMap<>();
        parmasMap.put("orderId",orderId);
        parmasMap.put("userId",userId);
        return ordersMapper.selectByOrderIdAndUserId(parmasMap);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateOrderStatus(String orderId, Integer orderStatus) {
        OrderStatus paidStatus = new OrderStatus();
        paidStatus.setOrderId(orderId);
        paidStatus.setOrderStatus(orderStatus);
        paidStatus.setPayTime(new Date());

        return orderStatusMapper.updateByPrimaryKeySelective(paidStatus);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public OrderStatus queryOrderStatusByOrderId(String orderId) {
        return orderStatusMapper.selectByPrimaryKey(orderId);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int closeOrder() {
        int closeCount = 0;
        List<OrderStatus> orderStatusList = orderStatusMapper.selectAllByOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        for (OrderStatus orderStatus: orderStatusList) {
            Date createdTime = orderStatus.getCreatedTime();
            int days = DateUtil.daysBetween(createdTime,new Date());
            if(days >= 1) {
                // 超过1天,订单关闭
                closeCount += doCloseOrder(orderStatus.getOrderId());
            }
        }
        return closeCount;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    int doCloseOrder(String orderId){
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.CLOSE.type);
        orderStatus.setCloseTime(new Date());
        return orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }
}
