package com.tcbd.coverboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.tcbd.coverboot.entity.Alarmbd;
import com.tcbd.coverboot.entity.Coverbd;
import com.tcbd.coverboot.service.CoverService;
import com.tcbd.coverboot.util.SnowflakeIdWorker;
import org.apache.catalina.filters.ExpiresFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List; 

@RestController
@RequestMapping("/hello")
public class  CoverController {

    @Autowired 
    private  CoverService coverService;

    public static Logger logger = LoggerFactory.getLogger(CoverController.class);

    /*@RequestMapping(value = "/receive/{position}",method = RequestMethod.POST)
    public List<CoverDao> getbyName(@PathVariable String position){
        return coverService.getByPosition(position);
    }*/

    @RequestMapping(value = "/receive", method = {RequestMethod.POST, RequestMethod.GET})
    private StringBuffer receive(HttpServletRequest request) {
        StringBuffer resultStr = new StringBuffer("");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer("");
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
            //真正接收到的数据
            //String result = sb.toString();
            //System.out.println("接收:"+result);
            //测试的数据
            //String result = "{\"upPacketSN\":-1,\"upDataSN\":-1,\"topic\":\"v1/up/ad19\",\"timestamp\":1602745069687,\"tenantId\":\"2000015719\",\"serviceId\":3,\"protocol\":\"lwm2m\",\"productId\":\"10095421\",\"payload\":{\"terminal_type\":\"TLJJ-2520\",\"software_version\":\"B3.5\",\"sinr\":300,\"rsrp\":-818,\"pci\":65,\"manufacturer_name\":\"TengLian\",\"heartbeat_time\":10.0,\"hardware_version\":\"VB01\",\"ecl\":0,\"cell_id\":99326931,\"battery_voltage\":3.640625,\"battery_value\":100,\"IMSI\":\"460113049459669\",\"IMEI\":\"861878041066389\",\"ICCID\":\"89861120204002343376\"},\"messageType\":\"dataReport\",\"deviceType\":\"\",\"deviceId\":\"df5bc90025bd4985a4320e964b424c77\",\"assocAssetId\":\"\",\"IMSI\":\"undefined\",\"IMEI\":\"861878041066389\"}";
            String result = "{\"upPacketSN\":-1,\"upDataSN\":-1,\"topic\":\"v1/up/ad19\",\"timestamp\":1602745069687,\"tenantId\":\"2000015719\",\"serviceId\":4,\"protocol\":\"lwm2m\",\"productId\":\"10095421\",\"payload\":{\"manhole_cover_position_state\":1,\"manhole_cover_open_state\":0,\"lean_angle\":82,\"battery_voltage\":3.640625,\"battery_value\":90},\"messageType\":\"dataReport\",\"deviceType\":\"\",\"deviceId\":\"df5bc90025bd4985a4320e964b424c77\",\"assocAssetId\":\"\",\"IMSI\":\"undefined\",\"IMEI\":\"861878041066389\"}\n";
            //String result ="{\"timestamp\":1602490249041,\"tenantId\":\"2000015719\",\"serviceId\":1001,\"protocol\":\"lwm2m\",\"productId\":\"10095421\",\"messageType\":\"eventReport\",\"eventType\":2,\"eventContent\":{\"manhole_cover_position_state\":1},\"deviceSn\":\"\",\"deviceId\":\"860e372828a641c4ae4584fc5e21c3e3\",\"IMSI\":\"undefined\",\"IMEI\":\"868474046693741\"}";
            //String result ="{\"timestamp\":1602490250321,\"tenantId\":\"2000015719\",\"serviceId\":1003,\"protocol\":\"lwm2m\",\"productId\":\"10095421\",\"messageType\":\"eventReport\",\"eventType\":2,\"eventContent\":{\"water_level_state\":1},\"deviceSn\":\"\",\"deviceId\":\"860e372828a641c4ae4584fc5e21c3e3\",\"IMSI\":\"undefined\",\"IMEI\":\"868474046693741\"}";
            //String result ="{\"timestamp\":1602490348034,\"tenantId\":\"2000015719\",\"serviceId\":1004,\"protocol\":\"lwm2m\",\"productId\":\"10095421\",\"messageType\":\"eventReport\",\"eventType\":2,\"eventContent\":{\"manhole_cover_open_state\":1},\"deviceSn\":\"\",\"deviceId\":\"860e372828a641c4ae4584fc5e21c3e3\",\"IMSI\":\"undefined\",\"IMEI\":\"868474046693741\"}";


            JSONObject json = JSONObject.parseObject(result);
            String serviceId = json.getString("serviceId");

            //心跳数据
            if(serviceId.equals("3")){
                JSONObject jsonchild = json.getJSONObject("payload");

                String imei = json.getString("IMEI");
                String postime = json.getString("timestamp");
                String protocol = json.getString("protocol");

                String iccid = jsonchild.getString("ICCID");
                String imsi = jsonchild.getString("IMSI");

                String sinr = jsonchild.getString("sinr");
                String rsrp = jsonchild.getString("rsrp");
                String pci = jsonchild.getString("pci");
                String ecl = jsonchild.getString("ecl");
                String cell_id = jsonchild.getString("cell_id");
                String battery_voltage = jsonchild.getString("battery_voltage");
                String battery_value = jsonchild.getString("battery_value");

                Coverbd coverbd = new Coverbd();
                coverbd.setIMEI(imei);
                coverbd.setCCID(iccid);
                coverbd.setIMSI(imsi);
                coverbd.setSINR(sinr);
                coverbd.setRSRP(rsrp);
                coverbd.setPCI(pci);
                coverbd.setECL(ecl);
                coverbd.setCELLID(cell_id);
                coverbd.setBatteryValue(battery_value);
                coverbd.setBatteryVoltage(battery_voltage);
                coverbd.setPosTime(stampToDate(postime));
                coverbd.setCreateTime(stampToDate(postime));
                coverbd.setProtocol(protocol);

                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1,1,1);
                long id = idWorker.nextId();

                coverbd.setId(id);
                //System.out.println(Long.toBinaryString(id));

                int i = coverService.insertDeviceHistory(coverbd);
                int j = coverService.updateDeviceCurrent(coverbd);
                if(i!=0){
                    logger.info("INSERT DEVICEHISTORY SUCCESS");
                }
                if(j!=0){
                    logger.info("UPDATE DEVICECURRENT SUCCESS");
                }

            }

            if(serviceId.equals("4")){
                JSONObject jsonchild = json.getJSONObject("payload");

                String imei = json.getString("IMEI");
                String postime = json.getString("timestamp");

                String manhole_cover_position_state = jsonchild.getString("manhole_cover_position_state");
                String manhole_cover_open_state = jsonchild.getString("manhole_cover_open_state");
                String lean_angle = jsonchild.getString("lean_angle");
                String battery_voltage = jsonchild.getString("battery_voltage");
                String battery_value = jsonchild.getString("battery_value");

                Coverbd coverbd = new Coverbd();
                coverbd.setIMEI(imei);
                coverbd.setLeanAngle(lean_angle);
                coverbd.setEnumPositionState(manhole_cover_position_state);
                coverbd.setEnumOpenState(manhole_cover_open_state);
                coverbd.setBatteryValue(battery_value);
                coverbd.setBatteryVoltage(battery_voltage);
                coverbd.setPosTime(stampToDate(postime));
                coverbd.setCreateTime(stampToDate(postime));

                int i = coverService.updateDeviceHistory(coverbd);
                int j = coverService.updateDeviceCurrent(coverbd);
                if(j!=0){
                    logger.info("UPDATE DEVICECURRENT SUCCESS");
                }
                if(i!=0){
                    logger.info("UPDATE DEVICEHISTORY SUCCESS");
                }

            }

            if(serviceId.equals("1001")){
                JSONObject jsonchild = json.getJSONObject("eventContent");
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(2,1,1);
                long id = idWorker.nextId();

                Alarmbd alarmbd = new Alarmbd();
                alarmbd.setId(id);
                alarmbd.setIMEI(json.getString("IMEI"));
                alarmbd.setEnumAlarm(jsonchild.getString("manhole_cover_position_state"));
                alarmbd.setAlarmContent("manhole_cover_position_state");
                alarmbd.setAlarmTime(stampToDate(json.getString("timestamp")));

                int i = coverService.insertDeviceAlarm(alarmbd);
                if(i!=0){
                    logger.info("INSERT DEVICEALARM SUCCESS");
                }
            }
            if(serviceId.equals("1003")){
                JSONObject jsonchild = json.getJSONObject("eventContent");
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(2,1,1);
                long id = idWorker.nextId();

                Alarmbd alarmbd = new Alarmbd();
                alarmbd.setId(id);
                alarmbd.setIMEI(json.getString("IMEI"));
                alarmbd.setEnumAlarm(jsonchild.getString("water_level_state"));
                alarmbd.setAlarmContent("water_level_state");
                alarmbd.setAlarmTime(stampToDate(json.getString("timestamp")));

                int i = coverService.insertDeviceAlarm(alarmbd);
                if(i!=0){
                    logger.info("INSERT DEVICEALARM SUCCESS");
                }

            }
            if(serviceId.equals("1004")){
                JSONObject jsonchild = json.getJSONObject("eventContent");
                SnowflakeIdWorker idWorker = new SnowflakeIdWorker(2,1,1);
                long id = idWorker.nextId();

                Alarmbd alarmbd = new Alarmbd();
                alarmbd.setId(id);
                alarmbd.setIMEI(json.getString("IMEI"));
                alarmbd.setEnumAlarm(jsonchild.getString("manhole_cover_open_state"));
                alarmbd.setAlarmContent("manhole_cover_open_state");
                alarmbd.setAlarmTime(stampToDate(json.getString("timestamp")));

                int i = coverService.insertDeviceAlarm(alarmbd);
                if(i!=0){
                    logger.info("INSERT DEVICEALARM SUCCESS");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr;
    }

    /*
     * 时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}


