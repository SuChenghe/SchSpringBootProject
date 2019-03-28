package com.suchenghe.service.event;

import com.suchenghe.dao.mysql.mybatis.pojo.DeviceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author SuChenghe
 * @date 2019/1/28 13:50
 */
@Component
public class MyPushEvent {

    @Autowired
    ApplicationContext applicationContext;

    private void schduleJob() {
        DeviceBean deviceBean = new DeviceBean();
        deviceBean.setDeviceCode("123456789");
        applicationContext.publishEvent(new MyApplicationEvent(this, deviceBean));
        System.out.println(123);
    }
}
