package com.tcbd.coverboot.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Coverbd {

    protected  String IMEI;
    protected  long Id;     //  雪花算法
  
    //心跳信息
    protected  String CCID;
    protected  String IMSI;
    protected  String SINR;
    protected  String RSRP;
    protected  String PCI;
    protected  String ECL;
    protected  String CELLID;
    protected  String BatteryValue;
    protected  String BatteryVoltage;

    //井盖状态信息
    protected  String EnumPositionState;    //定位状态
    protected  String EnumOpenState;        //闭合状态
    protected  String LeanAngle;            //倾斜角

    //其他信息
    protected  String Protocol;             //协议
    protected  String PosTime;
    protected  String CreateTime;


    protected  int count;                   //判断数据是否存在

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

    public String getCCID() {
        return CCID;
    }

    public void setCCID(String CCID) {
        this.CCID = CCID;
    }

    public String getIMSI() {
        return IMSI;
    }

    public void setIMSI(String IMSI) {
        this.IMSI = IMSI;
    }

    public String getSINR() {
        return SINR;
    }

    public void setSINR(String SINR) {
        this.SINR = SINR;
    }

    public String getRSRP() {
        return RSRP;
    }

    public void setRSRP(String RSRP) {
        this.RSRP = RSRP;
    }

    public String getPCI() {
        return PCI;
    }

    public void setPCI(String PCI) {
        this.PCI = PCI;
    }

    public String getECL() {
        return ECL;
    }

    public void setECL(String ECL) {
        this.ECL = ECL;
    }

    public String getCELLID() {
        return CELLID;
    }

    public void setCELLID(String CELLID) {
        this.CELLID = CELLID;
    }

    public String getBatteryValue() {
        return BatteryValue;
    }

    public void setBatteryValue(String batteryValue) {
        BatteryValue = batteryValue;
    }

    public String getBatteryVoltage() {
        return BatteryVoltage;
    }

    public void setBatteryVoltage(String batteryVoltage) {
        BatteryVoltage = batteryVoltage;
    }

    public String getEnumPositionState() {
        return EnumPositionState;
    }

    public void setEnumPositionState(String enumPositionState) {
        EnumPositionState = enumPositionState;
    }

    public String getEnumOpenState() {
        return EnumOpenState;
    }

    public void setEnumOpenState(String enumOpenState) {
        EnumOpenState = enumOpenState;
    }

    public String getLeanAngle() {
        return LeanAngle;
    }

    public void setLeanAngle(String leanAngle) {
        LeanAngle = leanAngle;
    }

    public String getProtocol() {
        return Protocol;
    }

    public void setProtocol(String protocol) {
        Protocol = protocol;
    }

    public String getPosTime() {
        return PosTime;
    }

    public void setPosTime(String posTime) {
        PosTime = posTime;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Coverbd{" +
                "IMEI='" + IMEI + '\'' +
                ", Id=" + Id +
                ", CCID='" + CCID + '\'' +
                ", IMSI='" + IMSI + '\'' +
                ", SINR='" + SINR + '\'' +
                ", RSRP='" + RSRP + '\'' +
                ", PCI='" + PCI + '\'' +
                ", ECL='" + ECL + '\'' +
                ", CELLID='" + CELLID + '\'' +
                ", BatteryValue='" + BatteryValue + '\'' +
                ", BatteryVoltage='" + BatteryVoltage + '\'' +
                ", EnumPositionState='" + EnumPositionState + '\'' +
                ", EnumOpenState='" + EnumOpenState + '\'' +
                ", LeanAngle='" + LeanAngle + '\'' +
                ", Protocol='" + Protocol + '\'' +
                ", PosTime='" + PosTime + '\'' +
                '}';
    }

    public Coverbd(){}

    public Coverbd(String IMEI, long id, String CCID, String IMSI, String SINR, String RSRP,
                   String PCI, String ECL, String CELLID, String posTime) {
        this.IMEI = IMEI;
        Id = id;
        this.CCID = CCID;
        this.IMSI = IMSI;
        this.SINR = SINR;
        this.RSRP = RSRP;
        this.PCI = PCI;
        this.ECL = ECL;
        this.CELLID = CELLID;
        PosTime = posTime;
    }

    public Coverbd(String IMEI, long id, String CCID, String IMSI, String SINR, String RSRP, String PCI,
                   String ECL, String CELLID, String batteryValue, String batteryVoltage,
                   String leanAngle) {
        this.IMEI = IMEI;
        Id = id;
        this.CCID = CCID;
        this.IMSI = IMSI;
        this.SINR = SINR;
        this.RSRP = RSRP;
        this.PCI = PCI;
        this.ECL = ECL;
        this.CELLID = CELLID;
        BatteryValue = batteryValue;
        BatteryVoltage = batteryVoltage;
        LeanAngle = leanAngle;
    }

    public Coverbd(String IMEI, String batteryValue, String batteryVoltage,
                   String enumPositionState, String enumOpenState,
                   String leanAngle, String posTime) {
        this.IMEI = IMEI;
        BatteryValue = batteryValue;
        BatteryVoltage = batteryVoltage;
        EnumPositionState = enumPositionState;
        EnumOpenState = enumOpenState;
        LeanAngle = leanAngle;
        PosTime = posTime;
    }

    public Coverbd(String IMEI, long id, String CCID, String IMSI, String SINR,
                   String RSRP, String PCI, String ECL, String CELLID,
                   String batteryValue, String batteryVoltage, String enumPositionState,
                   String enumOpenState, String leanAngle, String protocol, String posTime,
                   String createTime, int count) {
        this.IMEI = IMEI;
        Id = id;
        this.CCID = CCID;
        this.IMSI = IMSI;
        this.SINR = SINR;
        this.RSRP = RSRP;
        this.PCI = PCI;
        this.ECL = ECL;
        this.CELLID = CELLID;
        BatteryValue = batteryValue;
        BatteryVoltage = batteryVoltage;
        EnumPositionState = enumPositionState;
        EnumOpenState = enumOpenState;
        LeanAngle = leanAngle;
        Protocol = protocol;
        PosTime = posTime;
        CreateTime = createTime;
        this.count = count;
    }
}
