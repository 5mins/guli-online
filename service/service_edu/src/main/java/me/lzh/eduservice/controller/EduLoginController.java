package me.lzh.eduservice.controller;

import lombok.extern.slf4j.Slf4j;
import me.lzh.common.utils.commonResult.R;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lizihao on 2020/8/1 19:10
 */
@RestController
@RequestMapping("/eduservice/user")
@Slf4j
@CrossOrigin  //解决跨域
public class EduLoginController {

    @PostMapping("/login")
    public R login() {
        log.info("login in");

        return R.ok().data("token", "admin1");
    }

    @GetMapping("/info")
    public R getinfo() {

        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
