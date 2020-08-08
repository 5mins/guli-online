package me.lzh.vod.service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by lizihao on 2020/8/6 17:05
 */

public interface VodService {
    //上传视频到阿里云
    String uploadVideoAly(MultipartFile file);
    //删除多个阿里云视频的方法
    void removeMoreAlyVideo(List videoIdList);
}
