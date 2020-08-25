package me.lzh.staservice.service;

import me.lzh.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author codeGenerator
 * @since 2020-08-25
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void registerCount(String day);

    //图表显示，返回两部分数据，日期json数组，数量json数组
    public Map<String, Object> getShowData(String type, String begin, String end);
}
