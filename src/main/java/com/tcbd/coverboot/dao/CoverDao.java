package com.tcbd.coverboot.dao;

import com.tcbd.coverboot.entity.Alarmbd;
import com.tcbd.coverboot.entity.Coverbd;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CoverDao {

    int insertDeviceHistory(Coverbd coverbd);       //历史数据
    int updateDeviceHistory(Coverbd coverbd);       //更新历史数据
    int insertOrUpdateDeviceCurrent(Coverbd coverbd);     //插入或更新当前数据
    int insertDeviceAlarm(Alarmbd alarmbd);         //报警信息

    //int insertRealtimeBasicData(Coverbd coverbd);
    //int insertRealtimeStateData(Coverbd coverbd);

    //List<Coverbd> getCoverBasicDataByIMEI(String IMEI);
    //List<Coverbd> getCoverStateDataByIMEI(String IMEI);

    //List<CoverDao> getByPosition(String position);
}