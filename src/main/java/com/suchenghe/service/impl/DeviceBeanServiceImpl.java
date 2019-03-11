package com.suchenghe.service.impl;

import com.suchenghe.dao.mysql.datasourcemapperfirst.DeviceBeanMapper;
import com.suchenghe.dao.mysql.datasourcemappersecond.DeviceBeanMapper2;
import com.suchenghe.dao.mysql.pojo.DeviceBean;
import com.suchenghe.service.DeviceBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SuChenghe
 * @date 2018/12/3 17:54
 */
@Service
public class DeviceBeanServiceImpl implements DeviceBeanService {

    @Autowired
    DeviceBeanMapper deviceBeanMapper;
    @Autowired
    DeviceBeanMapper2 deviceBeanMapper2;

    @Override
    public int insertDevice(DeviceBean deviceBean) {
        return deviceBeanMapper.insertDevice(deviceBean);
    }

    @Override
    public int updateDevice(DeviceBean deviceBean) {
        return deviceBeanMapper.updateDevice(deviceBean);
    }

    @Override
    public List<DeviceBean> getByCondition(DeviceBean deviceBean) {
        List<DeviceBean> list = deviceBeanMapper.getByCondition(deviceBean);
        return list;
    }

    @Override
    public List<DeviceBean> getByCondition2(DeviceBean deviceBean) {
        List<DeviceBean> list = deviceBeanMapper2.getByCondition(deviceBean);
        return list;
    }

    @Override
    public List<DeviceBean> getByIdList(List<Long> idList) {
        List<DeviceBean> list = deviceBeanMapper.getByIdList(idList);
        return list;
    }

    @Override
    public void clearCache(DeviceBean deviceBean) {

    }
}
