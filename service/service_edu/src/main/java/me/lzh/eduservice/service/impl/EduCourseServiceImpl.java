package me.lzh.eduservice.service.impl;

import me.lzh.eduservice.entity.EduCourse;
import me.lzh.eduservice.entity.vo.CourseInfoVo;
import me.lzh.eduservice.mapper.EduCourseMapper;
import me.lzh.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-08-04
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        return null;
    }
}
