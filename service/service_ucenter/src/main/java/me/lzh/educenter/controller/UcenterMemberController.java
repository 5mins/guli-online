package me.lzh.educenter.controller;


import io.swagger.annotations.ApiOperation;
import me.lzh.common.utils.JwtInfo;
import me.lzh.common.utils.JwtUtils;
import me.lzh.common.utils.commonResult.R;
import me.lzh.common.utils.ordervo.MemberInfoVoOrder;
import me.lzh.educenter.entity.UcenterMember;
import me.lzh.educenter.entity.vo.LoginVo;
import me.lzh.educenter.entity.vo.MemberInfoVo;
import me.lzh.educenter.entity.vo.RegisterVo;
import me.lzh.educenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author codeGenerator
 * @since 2020-08-09
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    //登录
    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo){
        String token = memberService.login(loginVo);
        return R.ok().data("token", token).message("登录成功");
    }

    //注册
    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok().message("注册成功");
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId  = JwtUtils.getMemberIdByJwtToken(request);
                //查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        MemberInfoVo memberInfoVo = new MemberInfoVo();
        BeanUtils.copyProperties(member,memberInfoVo);
        return R.ok().data("userInfo",memberInfoVo);
    }

    //根据用户id获取用户信息
    @ApiOperation(value = "根据用户id获取用户信息 个人中心用")
    @PostMapping("getUserInfo/{id}")
    public R getUserInfo(@PathVariable String id) {

        UcenterMember member = memberService.getById(id);
        //把member对象里面值复制给UcenterMemberOrder对象
        MemberInfoVo memberInfoVo = new MemberInfoVo();
        BeanUtils.copyProperties(member,memberInfoVo);
        return R.ok().data("userInfo",memberInfoVo);
    }

    //根据用户id获取用户信息
    @ApiOperation(value = "根据用户id获取用户信息 订单中心用")
    @PostMapping("getUserInfoOrder/{id}")
    public MemberInfoVoOrder getUserInfoOrder(@PathVariable String id) {

        UcenterMember member = memberService.getById(id);
        //把member对象里面值复制给UcenterMemberOrder对象
        MemberInfoVoOrder userInfo = new MemberInfoVoOrder();
        BeanUtils.copyProperties(member,userInfo);
        return userInfo;
    }
}