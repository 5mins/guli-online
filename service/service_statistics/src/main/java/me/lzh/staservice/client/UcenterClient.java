package me.lzh.staservice.client;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.lzh.common.utils.commonResult.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by lizihao on 2020/8/25 15:28
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    //查询某天用户注册数
    @ApiOperation(value = "查询某天用户注册数")
    @GetMapping("/educenter/member/countRegister/{day}")
    public R countRegister(
            @ApiParam(name = "day", value = "具体日期：2020-10-10", required = true)
            @PathVariable("day") String day);
}
