package me.lzh.eduservice.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.val;
import me.lzh.common.utils.JwtUtils;
import me.lzh.common.utils.commonResult.R;
import me.lzh.common.utils.ordervo.CourseWebVoOrder;
import me.lzh.eduservice.client.OrdersClient;
import me.lzh.eduservice.entity.EduCourse;
import me.lzh.eduservice.entity.frontvo.CourseFrontVo;
import me.lzh.eduservice.entity.frontvo.CourseWebVo;
import me.lzh.eduservice.entity.vo.ChapterVo;
import me.lzh.eduservice.service.EduChapterService;
import me.lzh.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/coursefront")
//@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrdersClient ordersClient;

    //1 条件查询带分页查询课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        //返回分页所有数据
        return R.ok().data(map);
    }

    //2 课程详情的方法
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);

        //根据课程id和用户id查询当前课程是否已经支付过了
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        boolean buyCourse=false;
        if(!StringUtils.isEmpty(memberId)){
            buyCourse = ordersClient.isBuyCourse(courseId, memberId);
        }

        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList).data("isBuy",buyCourse);

    }

    //根据课程ID 查询课程信息
    @PostMapping("getCourseInfoOrder/{courseId}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String courseId){
        CourseWebVo baseCourseInfo = courseService.getBaseCourseInfo(courseId);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(baseCourseInfo,courseWebVoOrder);
        return courseWebVoOrder;

    }

}

