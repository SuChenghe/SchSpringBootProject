package com.suchenghe.dao.mysql.jpa.pojo;

import lombok.Data;

import javax.persistence.*;

import java.util.Date;

/**
 * @Entity 是一个必选的注解，声明这个类对应了一个数据库表。
 *
 * @author SuChenghe
 * @date 2018/12/3 17:26
 */
@Entity
@Table(name = "device_bean")
@Data
public class JpaDeviceBean {
    /**
     * 设备ID
     * GenerationType.IDENTITY 使用mysql的自增长
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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