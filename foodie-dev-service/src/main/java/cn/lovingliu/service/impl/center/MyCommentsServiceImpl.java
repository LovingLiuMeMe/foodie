package cn.lovingliu.service.impl.center;

import cn.lovingliu.common.enums.YesOrNo;
import cn.lovingliu.common.page.PagedGridResult;
import cn.lovingliu.mapper.ItemsCommentsMapperCustom;
import cn.lovingliu.mapper.OrderItemsMapper;
import cn.lovingliu.mapper.OrderStatusMapper;
import cn.lovingliu.mapper.OrdersMapper;
import cn.lovingliu.pojo.OrderItems;
import cn.lovingliu.pojo.OrderStatus;
import cn.lovingliu.pojo.Orders;
import cn.lovingliu.pojo.bo.center.OrderItemsCommentBO;
import cn.lovingliu.pojo.vo.MyCommentVO;
import cn.lovingliu.service.BaseService;
import cn.lovingliu.service.center.MyCommentsService;
import com.github.pagehelper.PageHelper;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyCommentsServiceImpl extends BaseService implements MyCommentsService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderStatusMapper orderStatusMapper;
    @Autowired
    private OrderItemsMapper orderItemsMapper;
    @Autowired
    private ItemsCommentsMapperCustom itemsCommentsMapperCustom;
    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<OrderItems> queryPendingComment(String orderId) {
        List<OrderItems> orderItemsList = orderItemsMapper.selectAllByOrderId(orderId);
        return orderItemsList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentList) {
        // 1.保存评价
        for (OrderItemsCommentBO oic: commentList) {
            oic.setCommentId(sid.nextShort());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("commentList",commentList);
        itemsCommentsMapperCustom.saveComemnts(map);

        // 2.修改订单评价状态
        Orders order = new Orders();
        order.setId(orderId);
        order.setIsComment(YesOrNo.YES.type);
        ordersMapper.updateByPrimaryKeySelective(order);

        // 3.修改订单状态表的留言时间
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCommentTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryMyComments(String userId,
                                           Integer page,
                                           Integer pageSize) {
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("userId", userId);
        PageHelper.startPage(page, pageSize);
        List<MyCommentVO> commentVOList = itemsCommentsMapperCustom.queryMyComments(paramsMap);
        return setterPagedGrid(commentVOList,page);
    }
}
