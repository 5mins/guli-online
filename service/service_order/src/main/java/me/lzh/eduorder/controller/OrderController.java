package me.lzh.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.lzh.common.utils.JwtInfo;
import me.lzh.common.utils.JwtUtils;
import me.lzh.common.utils.commonResult.R;
import me.lzh.eduorder.entity.Order;
import me.lzh.eduorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author codeGenerator
 * @since 2020-08-24
 */
@RestController
@RequestMapping("/eduorder/order")
//@CrossOrigin
@Api(description="订单服务相关")
public class OrderController {

    @Autowired
    OrderService orderService;


    /**
     * 生成订单的方法
     * @param courseId
     * @param request
     * @return
     */
    @PostMapping("/createOrder/{courseId}")
    @ApiOperation(value = "生成订单")
    public R saveOrder(@PathVariable String courseId,
                       HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        String orderId = orderService.createOrders(courseId,memberId);

        return R.ok().data("orderId",orderId);
    }

    /**
     * 根据订单ID 查询订单信息
     * @param courseId
     * @return
     */
    @GetMapping("/getOrderInfo/{courseId}")
    @ApiOperation(value = "根据订单id查询订单信息")
    public R getOrderInfo(@PathVariable String courseId){

        QueryWrapper<Order> wrapper = new  QueryWrapper<>();
        wrapper.eq("order_no",courseId);
        Order order = orderService.getOne(wrapper);
        return R.ok().data("item",order);
    }

    //根据课程id和用户id查询订单表中订单状态
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable String courseId,@PathVariable String memberId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);//支付状态 1代表已经支付
        int count = orderService.count(wrapper);
        if(count>0) { //已经支付
            return true;
        } else {
            return false;
        }
    }

}

