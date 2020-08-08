## 谷粒学院后台开发项目

最近想熟悉一下spring cloud微服务，发现了B站这个教程，挺不错的，于是跟着视频把代码敲了一遍，并且做了些小更改，能正常运行。

这是后台接口部分的代码，主要使用了SpringBoot+SpringCloud进行微服务架构，使用Feign、Gateway、Hystrix，以及阿里巴巴的Nacos等组件搭建了项目的基础环境。项目中还使用MyBatisPlus进行持久层的操作，使用了OAuth2+JWT实现了分布式的访问，项目中整合了SpringSecurity进行了权限控制。
除此之外，项目中使用了阿里巴巴的EasyExcel实现对Excel的读写操作，使用了Redis进行首页数据的缓存，使用Git进行代码的版本控制，还整合了Swagger生成接口文档 。



B站地址：[https://www.bilibili.com/video/BV1dQ4y1A75e](https://www.bilibili.com/video/BV1dQ4y1A75e)