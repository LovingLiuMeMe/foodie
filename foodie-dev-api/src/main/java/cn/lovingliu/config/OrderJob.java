package cn.lovingliu.config;

import cn.lovingliu.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author：LovingLiu
 * @Description: 定时任务,将未支付的订单改为结束状态
 * @Date：Created in 2020-01-06
 */
@Component
@Slf4j
public class OrderJob {

    /**
     * 使用定时任务的缺点
     * 1.会有时间差
     * 2.不支持集群(多个节点都在同时执行)
         * 单机可行
         * 解决方案: 将所有的定时任务单独部署到一台服务器上
     * 3.会对数据库进行全表搜索,及其影响数据库性能
     *
     * 定时任务,仅仅使用与小型的轻量级别项目
     *
     * 电商:消息队列  MQ
     * 延时任务
     *  10:12 分下单,未付款（10）状态 => 创建一个延时任务(11:12分执行) => 11:12分延时任务执行,判断订单状态是否还是未付款 => 是 直接关闭
     *  整个过程只执行一次, 只查询一个订单, 只进行一次修改操作
     */


    @Autowired
    private OrdersService ordersService;

    @Scheduled(cron = "0 0 0/2 * * ?")
    public void autoCloseOrder() {
        int closeCount = ordersService.closeOrder();
        log.info("=========关闭超时订单数量: {} ==========",closeCount);
    }
}
