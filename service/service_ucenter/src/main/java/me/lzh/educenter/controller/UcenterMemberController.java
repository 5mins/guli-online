package me.lzh.educenter.controller;


import me.lzh.common.utils.JwtUtils;
import me.lzh.common.utils.commonResult.R;
import me.lzh.educenter.entity.UcenterMember;
import me.lzh.educenter.entity.vo.MemberInfoVo;
import me.lzh.educenter.entity.vo.RegisterVo;
import me.lzh.educenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Member;

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
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member) {
        //member对象封装手机号和密码
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token = memberService.login(member);
        return R.ok().data("token",token);
    }

    //注册
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        MemberInfoVo memberInfoVo = new MemberInfoVo();
        BeanUtils.copyProperties(member,memberInfoVo);
        return R.ok().data("userInfo",memberInfoVo);
    }
}