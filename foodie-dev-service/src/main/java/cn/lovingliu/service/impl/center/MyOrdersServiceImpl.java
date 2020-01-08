package cn.lovingliu.service.impl.center;

import cn.lovingliu.common.enums.OrderStatusEnum;
import cn.lovingliu.common.enums.YesOrNo;
import cn.lovingliu.common.page.PagedGridResult;
import cn.lovingliu.mapper.OrderStatusMapper;
import cn.lovingliu.mapper.OrdersMapper;
import cn.lovingliu.mapper.OrdersMapperCustom;
import cn.lovingliu.pojo.OrderStatus;
import cn.lovingliu.pojo.Orders;
import cn.lovingliu.pojo.vo.MyOrdersVO;
import cn.lovingliu.pojo.vo.OrderStatusCountsVO;
import cn.lovingliu.service.BaseService;
import cn.lovingliu.service.center.MyOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyOrdersServiceImpl extends BaseService implements MyOrdersService {
    @Autowired
    private OrdersMapperCustom ordersMapperCustom;
    @Autowired
    private OrderStatusMapper orderStatusMapper;
    @Autowired
    private OrdersMapper ordersMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryMyOrders(String userId,
                                         Integer orderStatus,
                                         Integer page,
                                         Integer pageSize) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId", userId);
        if (orderStatus != null){
            map.put("orderStatus",orderStatus);
        }

        PageHelper.startPage(page,pageSize);
        List<MyOrdersVO> list = ordersMapperCustom.queryMyOrders(map);
        return setterPagedGrid(list,page);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int updateDeliverOrderStatus(String orderId) {
        OrderStatus orderStatus = orderStatusMapper.selectByPrimaryKey(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_RECEIVE.type);
        orderStatus.setDeliverTime(new Date());
        return orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Orders queryMyOrder(String userId, String orderId) {
        Map<String,Object> parmasMap = new HashMap<>();
        parmasMap.put("orderId",orderId);
        parmasMap.put("userId",userId);
        return ordersMapper.selectByOrderIdAndUserId(parmasMap);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean updateReceiverOrderStatus(String orderId) {
        OrderStatus updateOrder = orderStatusMapper.selectByPrimaryKey(orderId);
        if(updateOrder.getOrderStatus() != OrderStatusEnum.WAIT_RECEIVE.type){
            return false;
        }
        updateOrder.setOrderStatus(OrderStatusEnum.SUCCESS.type);
        updateOrder.setSuccessTime(new Date());

        int count = orderStatusMapper.updateByPrimaryKeySelective(updateOrder);
        return count == 1;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteOrder(String userId, String orderId) {
        Orders updateOrder = ordersMapper.selectByPrimaryKey(orderId);
        if(!updateOrder.getUserId().equals(userId)){
            return false;
        }
        updateOrder.setIsDelete(YesOrNo.YES.type);
        updateOrder.setUpdatedTime(new Date());

        int count = ordersMapper.updateByPrimaryKeySelective(updateOrder);
        return count == 1;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public OrderStatusCountsVO getOrderStatusCounts(String userId) {
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("userId", userId);
        paramsMap.put("orderStatus",OrderStatusEnum.WAIT_PAY.type);// 待付款
        int waitPayCounts = ordersMapperCustom.getMyOrderStatusCounts(paramsMap);
        paramsMap.put("orderStatus",OrderStatusEnum.WAIT_DELIVER.type); // 已收款,待发货
        int waitDeliverCounts = ordersMapperCustom.getMyOrderStatusCounts(paramsMap);
        paramsMap.put("orderStatus",OrderStatusEnum.WAIT_RECEIVE.type); // 已发货,待收货
        int waitReceiveCounts = ordersMapperCustom.getMyOrderStatusCounts(paramsMap);

        paramsMap.put("orderStatus",OrderStatusEnum.SUCCESS.type); //交易成功,待评价
        paramsMap.put("isComemnt", YesOrNo.NO.type);
        int waitCommentCounts = ordersMapperCustom.getMyOrderStatusCounts(paramsMap);
        OrderStatusCountsVO countsVO = new OrderStatusCountsVO(waitPayCounts,
                waitDeliverCounts,
                waitReceiveCounts,
                waitCommentCounts);
        return countsVO;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult getOrdersTrend(String userId, Integer page, Integer pageSize) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId", userId);

        PageHelper.startPage(page,pageSize);
        List<OrderStatus> orderStatusList = ordersMapperCustom.getMyOrderTrend(map);
        return setterPagedGrid(orderStatusList,page);
    }

}
