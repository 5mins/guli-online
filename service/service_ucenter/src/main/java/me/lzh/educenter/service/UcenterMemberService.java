package me.lzh.educenter.service;

import me.lzh.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import me.lzh.educenter.entity.vo.LoginVo;
import me.lzh.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author codeGenerator
 * @since 2020-08-09
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    //登录的方法
    String login(LoginVo loginvo);

    //注册的方法
    void register(RegisterVo registerVo);

    //查询某天用户注册数
    Integer countRegisterDay(String day);
}