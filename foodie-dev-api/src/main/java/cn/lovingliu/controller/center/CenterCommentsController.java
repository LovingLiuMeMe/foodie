package cn.lovingliu.controller.center;

import cn.lovingliu.common.ServerResponse;
import cn.lovingliu.common.enums.YesOrNo;
import cn.lovingliu.common.page.PagedGridResult;
import cn.lovingliu.pojo.OrderItems;
import cn.lovingliu.pojo.Orders;
import cn.lovingliu.pojo.bo.center.OrderItemsCommentBO;
import cn.lovingliu.service.OrdersService;
import cn.lovingliu.service.center.MyCommentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 用户中心订单评价Controller
 * @Date：Created in 2020-01-06
 */
@Api(value = "center - 用户中心订单评价模块",tags = "用户中心订单评价模块")
@RestController
@RequestMapping("/mycomments")
public class CenterCommentsController {
    @Autowired
    private MyCommentsService myCommentsService;
    @Autowired
    private OrdersService ordersService;

    @ApiOperation(value = "查询订单评价列表",notes = "查询订单评价列表",httpMethod = "POST")
    @PostMapping("/pending")
    public ServerResponse pending(@RequestParam String userId,
                                @RequestParam String orderId){
        Orders orders = ordersService.queryOrderByOrderIdAndUserId(orderId,userId);
        // 该订单是否存在
        if(orders == null){
            return ServerResponse.createByErrorMessage("订单不存在");
        }
        // 该订单是否已经被评价
        if(orders.getIsComment() == YesOrNo.YES.type) {
            return ServerResponse.createByErrorMessage("该笔订单已经被评价");
        }
        List<OrderItems> orderItemsList = myCommentsService.queryPendingComment(orderId);
        return ServerResponse.createBySuccess(orderItemsList);
    }

    @ApiOperation(value = "保存评论列表",notes = "保存评论列表",httpMethod = "POST")
    @PostMapping("/saveList")
    public ServerResponse saveList(@RequestParam String userId,
                                   @RequestParam String orderId,
                                   @RequestBody List<OrderItemsCommentBO> commentList){
        Orders orders = ordersService.queryOrderByOrderIdAndUserId(orderId,userId);
        // 该订单是否存在
        if(orders == null){
            return ServerResponse.createByErrorMessage("订单不存在");
        }
        // 判断评论内容不能为空
        if (commentList == null || commentList.isEmpty() || commentList.size() == 0){
            return ServerResponse.createByErrorMessage("评论内容不能为空");
        }
        myCommentsService.saveComments(orderId,userId,commentList);
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "查询我的评价",notes = "查询我的评价",httpMethod = "POST")
    @PostMapping("/query")
    public ServerResponse<PagedGridResult> comments (
            @ApiParam(name = "userId",value = "用户Id",required = true)
            @RequestParam("userId") String userId,

            @ApiParam(name = "page",value = "当前页码",required = false)
            @RequestParam(value = "page",defaultValue = "1") Integer page,

            @ApiParam(name = "pageSize",value = "每页显示的数量",required = false)
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {

        if(StringUtils.isBlank(userId)){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        PagedGridResult grid = myCommentsService.queryMyComments(userId,page,pageSize);
        return ServerResponse.createBySuccess(grid);
    }
}
