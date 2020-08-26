package me.lzh.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.lzh.common.utils.JwtInfo;
import me.lzh.common.utils.JwtUtils;
import me.lzh.common.utils.commonResult.R;
import me.lzh.eduservice.client.UcenterClient;
import me.lzh.eduservice.entity.EduComment;
import me.lzh.eduservice.service.EduCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author codeGenerator
 * @since 2020-08-23
 */
@RestController
@RequestMapping("/eduservice/edu-comment")
//@CrossOrigin
@Slf4j
public class EduCommentController {

    @Autowired
    private EduCommentService commentService;

    @Autowired
    private UcenterClient ucenterClient;


    //根据课程ID查询评论列表
    @ApiOperation("评论分页列表")
    @GetMapping("{getCommonList/{courseId}/{page}/{limit}}")
    public R index( @ApiParam(name = "page", value = "当前页码", required = true)
                        @PathVariable Long page,

                    @ApiParam(name = "limit", value = "每页记录数", required = true)
                        @PathVariable Long limit,

                    @ApiParam(name = "courseId", value = "查询对象", required = true)
                        @PathVariable  String courseId){
//        System.out.println(courseId);
        log.info("courseID"+courseId);

        Page<EduComment> pageParam = new Page<>(page,limit);
        QueryWrapper queryWrapper = new QueryWrapper();
        if(!StringUtils.isEmpty(courseId)){
            queryWrapper.eq("course_id",courseId);
        }
        commentService.page(pageParam,queryWrapper);
        List<EduComment> commentList = pageParam.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", pageParam.getCurrent());
        map.put("pages", pageParam.getPages());
        map.put("size", pageParam.getSize());
        map.put("total", pageParam.getTotal());
        map.put("hasNext", pageParam.hasNext());
        map.put("hasPrevious", pageParam.hasPrevious());
        return R.ok().data(map);

    }

    @ApiOperation(value = "添加评论")
    @PostMapping("saveComment")
    public R save(@RequestParam(value="content",required=true)String content,
                  @RequestParam(value="teacherId",required=true)String teacherId,
                  @RequestParam(value="courseId",required=true)String courseId,
                  HttpServletRequest request) {
        JwtInfo jwtToken = JwtUtils.getMemberInfwByJwtToken(request);
        if(StringUtils.isEmpty(jwtToken)) {
            return R.error().code(28004).message("请登录");
        }
        EduComment comment = new EduComment();
        comment.setMemberId(jwtToken.getId());
//        R result = ucenterClient.getUserInfoOrder("1");
        R result = ucenterClient.getUserInfoOrder(jwtToken.getId());
        Map<String, Object> userInfo = result.getData();

        LinkedHashMap userInfo1 = (LinkedHashMap)userInfo.get("userInfo");
        String nickname = (String)userInfo1.get("nickname");
        String avatar = (String)userInfo1.get("avatar");
        comment.setNickname(nickname);
        comment.setAvatar(avatar);

        //TOdo 这里最好加一个判断courseId和teacherId的有效性
        comment.setContent(content);
        comment.setCourseId(courseId);
        comment.setTeacherId(teacherId);

        commentService.save(comment);
        return R.ok();
    }

}

