package cn.lovingliu.service.center;

import cn.lovingliu.common.page.PagedGridResult;
import cn.lovingliu.pojo.Orders;
import cn.lovingliu.pojo.vo.OrderStatusCountsVO;

public interface MyOrdersService {
    /**
     * 分页查询我的订单列表
     * @param userId
     * @param orderStatus
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult queryMyOrders(String userId,
                                  Integer orderStatus,
                                  Integer page,
                                  Integer pageSize);

    /**
     * 订单发货
     * @param orderId
     * @return
     */
    int updateDeliverOrderStatus(String orderId);

    /**
     * 根据订单Id和用户ID查询单个订单
     * @param userId
     * @param orderId
     * @return
     */
    Orders queryMyOrder(String userId,String orderId);

    /**
     * 更新订单状态 -> 确认收货
     * @param orderId
     * @return
     */
    boolean updateReceiverOrderStatus(String orderId);

    /**
     * 删除订单(逻辑删除)
     * @param userId
     * @param orderId
     * @return
     */
    boolean deleteOrder(String userId, String orderId);

    /**
     * 查询不同订单状态的数量
     * @param userId
     * @return
     */
    OrderStatusCountsVO getOrderStatusCounts(String userId);

    /**
     * 获得订单的动向(分页)
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult getOrdersTrend(String userId,
                                  Integer page,
                                  Integer pageSize);
}
