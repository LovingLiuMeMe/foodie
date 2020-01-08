package cn.lovingliu.service.center;

import cn.lovingliu.common.page.PagedGridResult;
import cn.lovingliu.pojo.OrderItems;
import cn.lovingliu.pojo.bo.center.OrderItemsCommentBO;

import java.util.List;

public interface MyCommentsService {
    /**
     * 根据订单Id查询关联的商品
     * @param orderId
     * @return
     */
    List<OrderItems> queryPendingComment(String orderId);

    /**
     * 保存商品的评论
     * @param orderId
     * @param userId
     * @param commentList
     * @return
     */
    void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentList);

    /**
     * 我的评价查询（分页）
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult queryMyComments(String userId, Integer page,Integer pageSize);
}
