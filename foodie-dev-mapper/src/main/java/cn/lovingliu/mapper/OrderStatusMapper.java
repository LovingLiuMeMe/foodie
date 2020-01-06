package cn.lovingliu.mapper;

import cn.lovingliu.pojo.OrderStatus;

import java.util.List;

public interface OrderStatusMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(OrderStatus record);

    int insertSelective(OrderStatus record);

    OrderStatus selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderStatus record);

    int updateByPrimaryKey(OrderStatus record);

    List<OrderStatus> selectAllByOrderStatus(Integer orderStatus);
}