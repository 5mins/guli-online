package me.lzh.eduservice.controller;


import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.lzh.common.utils.commonResult.R;
import me.lzh.eduservice.entity.subject.OneSubject;
import me.lzh.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-08-03
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin  //解决跨域
@Slf4j
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;


    //添加课程分类
    //获取指定格式的excel文件，转换成课程分类，存到数据库
    @ApiOperation(value = "添加课程分类")
    @PostMapping("/addSubject")
    public R addSubject(@RequestParam("file") MultipartFile multipartFile){
        //上传excel文件
        if(multipartFile!=null){
            subjectService.saveSubject(multipartFile,subjectService);
            return R.ok();
        }else{
            log.error("MultipartFile is null !");
            return R.error().message("MultipartFile null ");
        }

    }

    @ApiOperation(value = "课程分类列表(树形)")
    @GetMapping("/getAllSubject")
    public R getAllSubject(){
        //list集合泛型是一级分类
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }

}

