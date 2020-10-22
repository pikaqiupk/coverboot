package com.tcbd.coverboot.serviceImpl;

import com.tcbd.coverboot.dao.CoverDao;
import com.tcbd.coverboot.entity.Alarmbd;
import com.tcbd.coverboot.entity.Coverbd;
import com.tcbd.coverboot.service.CoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CoverServiceImpl implements CoverService {

    @Autowired
    private CoverDao coverDao;

    @Override
    public int insertDeviceHistory(Coverbd coverbd) {
        return  coverDao.insertDeviceHistory(coverbd);
    }

    @Override
    public int updateDeviceHistory(Coverbd coverbd) {
        return coverDao.updateDeviceHistory(coverbd);
    }

    public int updateDeviceCurrent(Coverbd coverbd){
        return  coverDao.insertOrUpdateDeviceCurrent(coverbd);
    }

    @Override
    public int insertDeviceAlarm(Alarmbd alarmbd) {
        return coverDao.insertDeviceAlarm(alarmbd);
    }
}
