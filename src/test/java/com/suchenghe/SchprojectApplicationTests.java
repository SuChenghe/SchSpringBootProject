package com.suchenghe;

import com.alibaba.fastjson.JSON;
import com.suchenghe.dao.mysql.config.MysqlHikariDataSource1;
import com.suchenghe.dao.mysql.mybatis.pojo.DeviceBean;
import com.suchenghe.service.DeviceBeanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchprojectApplicationTests {

	@Autowired
	private DeviceBeanService deviceBeanService;

	@Autowired
	MysqlHikariDataSource1 mysqlHikariDataSource1;

	@Test
	public void contextLoads() {
		DeviceBean deviceBean = new DeviceBean();
		deviceBean.setDeviceStatus("1");
		List<DeviceBean> deviceBeanList =  deviceBeanService.getByCondition(deviceBean);
		System.out.println(JSON.toJSONString(deviceBeanList));
	}

	@Test
	public void mysqlHikariDataSource1Msg() {
		System.out.println(mysqlHikariDataSource1.getConnectionTimeout());
	}

}
