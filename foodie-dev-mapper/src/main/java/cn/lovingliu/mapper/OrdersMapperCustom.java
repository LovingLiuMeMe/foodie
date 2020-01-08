package cn.lovingliu.mapper;

import cn.lovingliu.pojo.OrderStatus;
import cn.lovingliu.pojo.vo.MyOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 自定义订单Mapper接口
 */
public interface OrdersMapperCustom {
    List<MyOrdersVO> queryMyOrders(@Param("paramsMap") Map<String,Object> paramsMap);
    int getMyOrderStatusCounts(@Param("paramsMap") Map<String,Object> paramsMap);
    List<OrderStatus> getMyOrderTrend(@Param("paramsMap") Map<String,Object> paramsMap);
}