package com.suchenghe.dao.mysql.jpa.dao;

import com.suchenghe.dao.mysql.jpa.pojo.JpaDeviceBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author SuChenghe
 * @date 2019/3/28 9:48
 */
public interface DeviceBeanDao  extends JpaRepository<JpaDeviceBean,Long> {
    List<JpaDeviceBean> findAllByDeviceStatus(String deviceStatus);
}
