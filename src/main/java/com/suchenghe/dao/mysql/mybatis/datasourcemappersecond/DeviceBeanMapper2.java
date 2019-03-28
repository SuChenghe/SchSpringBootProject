package com.suchenghe.dao.mysql.mybatis.datasourcemappersecond;

import com.suchenghe.dao.mysql.mybatis.pojo.DeviceBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DeviceBeanMapper2 {

    int insertDevice(DeviceBean deviceBean);

    int updateDevice(DeviceBean deviceBean);

    List<DeviceBean> getByCondition(DeviceBean deviceBean);

    List<DeviceBean> getByIdList(List<Long> idList);

}
