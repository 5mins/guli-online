package me.lzh.oss;

/**
 * Created by lizihao on 2020/8/2 16:38
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //exclude = DataSourceAutoConfiguration.class 不加载数据库配置
@ComponentScan(basePackages = {"me.lzh"})
@EnableDiscoveryClient
public class OssApplication {

    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class, args);
    }
}