package me.lzh.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lizihao on 2020/8/2 17:05
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
