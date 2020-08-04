package me.lzh.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.swing.*;

/**
 * Created by lizihao on 2020/7/30 23:13
 */

@SpringBootApplication
@ComponentScan(basePackages = {"me.lzh"})
//@EnableSwagger2
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
