package com.tcbd.coverboot.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Alarmbd {

    protected  String IMEI;
    protected  long Id;


    protected  String EnumAlarm;        //警报类型
    protected  String AlarmTime;
    protected  String AlarmContent;     //警报内容

    protected  String EnumAlarmState;   //警报状态
    protected  String CreatTime;        //


    public Alarmbd(){}

    public Alarmbd(String IMEI, long id, String enumAlarm,
                   String alarmTime, String alarmContent) {
        this.IMEI = IMEI;
        Id = id;
        EnumAlarm = enumAlarm;
        AlarmTime = alarmTime;
        AlarmContent = alarmContent;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getEnumAlarm() {
        return EnumAlarm;
    }

    public void setEnumAlarm(String enumAlarm) {
        EnumAlarm = enumAlarm;
    }

    public String getAlarmTime() {
        return AlarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        AlarmTime = alarmTime;
    }

    public String getAlarmContent() {
        return AlarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        AlarmContent = alarmContent;
    }

    public String getEnumAlarmState() {
        return EnumAlarmState;
    }

    public void setEnumAlarmState(String enumAlarmState) {
        EnumAlarmState = enumAlarmState;
    }

    public String getCreatTime() {
        return CreatTime;
    }

    public void setCreatTime(String creatTime) {
        CreatTime = creatTime;
    }

    @Override
    public String toString() {
        return "Alarmbd{" +
                "IMEI='" + IMEI + '\'' +
                ", Id=" + Id +
                ", EnumAlarm='" + EnumAlarm + '\'' +
                ", AlarmTime='" + AlarmTime + '\'' +
                ", AlarmContent='" + AlarmContent + '\'' +
                ", EnumAlarmState='" + EnumAlarmState + '\'' +
                ", CreatTime='" + CreatTime + '\'' +
                '}';
    }
}
