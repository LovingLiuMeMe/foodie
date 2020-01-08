package cn.lovingliu.controller.center;

import cn.lovingliu.common.ServerResponse;
import cn.lovingliu.common.page.PagedGridResult;
import cn.lovingliu.pojo.Orders;
import cn.lovingliu.pojo.vo.OrderStatusCountsVO;
import cn.lovingliu.service.center.MyOrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author：LovingLiu
 * @Description: 用户中心Controller
 * @Date：Created in 2020-01-06
 */
@Api(value = "用户中心我的订单列表",tags = "用户中心我的订单列表的相关接口")
@RestController
@RequestMapping("/myorders")
public class CenterOrdersController {
    @Autowired
    private MyOrdersService myOrdersService;

    @ApiOperation(value = "查询订单列表",notes = "查询订单列表",httpMethod = "POST")
    @PostMapping("/query")
    public ServerResponse<PagedGridResult> comments (
            @ApiParam(name = "userId",value = "用户Id",required = true)
            @RequestParam("userId") String userId,

            @ApiParam(name = "orderStatus",value = "订单状态",required = false)
            @RequestParam("orderStatus") Integer orderStatus,

            @ApiParam(name = "page",value = "当前页码",required = false)
            @RequestParam(value = "page",defaultValue = "1") Integer page,

            @ApiParam(name = "pageSize",value = "每页显示的数量",required = false)
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {

        if(StringUtils.isBlank(userId)){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        PagedGridResult grid = myOrdersService.queryMyOrders(userId,orderStatus,page,pageSize);
        return ServerResponse.createBySuccess(grid);
    }

    // 商家发货没有后端，所以这个接口仅仅只是用于模拟
    @ApiOperation(value="商家发货", notes="商家发货", httpMethod = "GET")
    @GetMapping("/deliver")
    public ServerResponse deliver(
            @ApiParam(name = "orderId", value = "订单id", required = true)
            @RequestParam String orderId) throws Exception {

        if (StringUtils.isBlank(orderId)) {
            return ServerResponse.createByErrorMessage("订单ID不能为空");
        }
        myOrdersService.updateDeliverOrderStatus(orderId);
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value="用户确认收货", notes="用户确认收货", httpMethod = "POST")
    @PostMapping("/confirmReceive")
    public ServerResponse confirmReceive(@RequestParam String userId,
                                         @RequestParam String orderId){
        Orders orders = myOrdersService.queryMyOrder(userId, orderId);
        if (orders == null){
            return ServerResponse.createByErrorMessage("订单不存在");
        }
        boolean res = myOrdersService.updateReceiverOrderStatus(orderId);
        if (!res){
            return ServerResponse.createByErrorMessage("订单收货失败");
        }
        return ServerResponse.createBySuccess("收货成功");
    }

    @ApiOperation(value="用户删除订单", notes="用户删除订单", httpMethod = "POST")
    @PostMapping("/delete")
    public ServerResponse delete(@RequestParam String userId,
                                 @RequestParam String orderId){
        Orders orders = myOrdersService.queryMyOrder(userId, orderId);
        if (orders == null){
            return ServerResponse.createByErrorMessage("订单不存在");
        }
        boolean res = myOrdersService.deleteOrder(userId, orderId);
        if (!res){
            return ServerResponse.createByErrorMessage("订单删除失败");
        }
        return ServerResponse.createBySuccess("订单删除成功");
    }

    @ApiOperation(value="获得订单状态数概况", notes="获得订单状态数概况", httpMethod = "POST")
    @PostMapping("/statusCounts")
    public ServerResponse statusCounts(@RequestParam String userId){
        if (StringUtils.isBlank(userId)){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        OrderStatusCountsVO result = myOrdersService.getOrderStatusCounts(userId);
        return ServerResponse.createBySuccess(result);
    }

    @ApiOperation(value="查询订单动向", notes="查询订单动向", httpMethod = "POST")
    @PostMapping("/trend")
    public ServerResponse trend(@ApiParam(name = "userId",value = "用户Id",required = true)
                                @RequestParam("userId") String userId,

                                @ApiParam(name = "page",value = "当前页码",required = false)
                                @RequestParam(value = "page",defaultValue = "1") Integer page,

                                @ApiParam(name = "pageSize",value = "每页显示的数量",required = false)
                                @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        if (StringUtils.isBlank(userId)){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        PagedGridResult grid = myOrdersService.getOrdersTrend(userId,page,pageSize);
        return ServerResponse.createBySuccess(grid);
    }
}
