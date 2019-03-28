package com.suchenghe.dao.mysql.mybatis.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author SuChenghe
 * @date 2018/12/3 17:26
 */
@Data
public class DeviceBean {
    /**
     * 设备ID
     */
    private Long id;
    /**
     * 设备编号
     */
    private String deviceCode;
    /**
     * 设备名称
     */
    private String deviceName;
    /**
     * 设备状态
     */
    private String deviceStatus;
    /**
     * 创建时间
     *
     * @return
     */
    private Date createTime;

}