package me.lzh.eduorder.service;

import me.lzh.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author codeGenerator
 * @since 2020-08-24
 */
public interface OrderService extends IService<Order> {

    String createOrders(String courseId, String memberId);
}
