package me.lzh.eduservice.client;

import me.lzh.common.utils.commonResult.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by lizihao on 2020/8/23 19:30
 */
@FeignClient(name = "service-ucenter",fallback = UcenterFileDegradeFeignClient.class) //调用的服务名称
@Component
public interface UcenterClient {
    //根据用户id获取用户信息
    @PostMapping("/educenter/member/getUserInfo/{id}")
    public R getUserInfoOrder(@PathVariable("id") String id) ;
}
