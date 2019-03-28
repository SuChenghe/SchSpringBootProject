package com.suchenghe.service.event;

import com.suchenghe.dao.mysql.mybatis.pojo.DeviceBean;
import org.springframework.context.ApplicationEvent;

import java.util.concurrent.TimeUnit;

/**
 * @author SuChenghe
 * @date 2019/1/28 9:56
 */
public class MyApplicationEvent extends ApplicationEvent {

    private DeviceBean deviceBean;

    public MyApplicationEvent(Object object, DeviceBean deviceBean) {
        super(object);
        this.deviceBean = deviceBean;
    }

    /**
     * 事件处理事项
     */
    public void printMsg() {
        System.out.println("开始执行");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(deviceBean.toString());
    }
}
