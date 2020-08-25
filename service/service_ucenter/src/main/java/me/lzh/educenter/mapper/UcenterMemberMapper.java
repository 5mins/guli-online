package me.lzh.educenter.mapper;

import me.lzh.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author codeGenerator
 * @since 2020-08-09
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
    //查询某天用户注册数
    Integer countRegisterDay(@Param("day")  String day);
}
