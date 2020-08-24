package me.lzh.eduorder.service.impl;

import me.lzh.common.utils.ordervo.CourseWebVoOrder;
import me.lzh.common.utils.ordervo.MemberInfoVoOrder;
import me.lzh.eduorder.client.EduClient;
import me.lzh.eduorder.client.UcenterClient;
import me.lzh.eduorder.entity.Order;
import me.lzh.eduorder.mapper.OrderMapper;
import me.lzh.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.lzh.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author codeGenerator
 * @since 2020-08-24
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    EduClient eduClient;

    @Autowired
    UcenterClient ucenterClient;
    /*
       1 生成订单的方法
         */
    @Override
    public String createOrders(String courseId, String memberId) {

        //远程获取用户信息
        MemberInfoVoOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberId);

        //远程获取课程信息
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);

        //创建Order对象，向order对象里面设置需要数据
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        baseMapper.insert(order);
        //返回订单号
        return order.getOrderNo();

    }


}
