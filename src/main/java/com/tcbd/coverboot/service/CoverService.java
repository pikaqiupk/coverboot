package com.tcbd.coverboot.service;

import com.tcbd.coverboot.entity.Alarmbd;
import com.tcbd.coverboot.entity.Coverbd;

public interface CoverService{
     int  insertDeviceHistory(Coverbd coverbd);
     int  updateDeviceHistory(Coverbd coverbd);
     int  updateDeviceCurrent(Coverbd coverbd);
     int  insertDeviceAlarm(Alarmbd alarmbd);
}

