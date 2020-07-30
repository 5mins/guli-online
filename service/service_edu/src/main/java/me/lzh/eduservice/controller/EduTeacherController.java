package me.lzh.eduservice.controller;


import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.lzh.eduservice.entity.EduTeacher;
import me.lzh.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-07-30
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
@Slf4j
public class EduTeacherController {


    //访问地址： http://localhost:8001/eduservice/edu-teacher/findAll
    //把service注入
    @Autowired
    private EduTeacherService teacherService;

    //1 查询讲师表所有数据
    //rest风格
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public List<EduTeacher> findAllTeacher() {

        log.info("findAllTeacher");
        //调用service的方法实现查询所有的操作
        List<EduTeacher> list = teacherService.list(null);
        return list;
    }

    //2 逻辑删除讲师的方法
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("/{id}")
    public boolean removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                           @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        return flag;
//        if(flag) {
//            return R.ok();
//        } else {
//            return R.error();
//        }
    }







}

