package me.lzh.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
@EqualsAndHashCode(callSuper = false) //lombok @data 这个因为咱在子类里面这么使用@EqualsAndHashCode(callSuper = false) ，不调用父类的属性，那么子类属性里面的相同的话，那hashcode的值就相同啦
public class GuliException extends RuntimeException {
    private Integer code;//状态码
    private String msg;//异常信息
}
