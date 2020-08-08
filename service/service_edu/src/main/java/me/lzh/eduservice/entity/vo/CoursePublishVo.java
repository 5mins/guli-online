package me.lzh.eduservice.entity.vo;

/**
 * Created by lizihao on 2020/8/4 20:47
 */
import lombok.Data;

@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
