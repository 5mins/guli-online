package me.lzh.eduorder.client;

import me.lzh.common.utils.ordervo.MemberInfoVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by lizihao on 2020/8/24 16:35
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    //根据用户id获取用户信息
    @PostMapping("/educenter/member/getUserInfoOrder/{id}")
    public MemberInfoVoOrder getUserInfoOrder(@PathVariable("id") String id);


}
