package me.lzh.eduservice.service;

import me.lzh.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import me.lzh.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-08-04
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);
}
