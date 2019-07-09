package com.suchenghe.web.device;

import com.alibaba.fastjson.JSON;
import com.suchenghe.dao.mysql.config.MysqlHikariDataSource1;
import com.suchenghe.dao.mysql.jpa.pojo.JpaDeviceBean;
import com.suchenghe.dao.mysql.mybatis.pojo.DeviceBean;
import com.suchenghe.service.DeviceBeanService;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Controller：修饰class，用来创建处理http请求的对象
 *
 * @RestController：Spring4之后加入的注解
 * 原来在@Controller中返回json需要@ResponseBody来配合
 * 如果直接用@RestController替代@Controller就不需要再配置@ResponseBody,默认返回json格式
 *
 * @author SuChenghe
 * @date 2018/9/26 09:39
 */
@Api(value = "设备请求控制器",description = "简单描述")
@Controller("device_controller")
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    protected DeviceBeanService deviceService;

    @Autowired
    MysqlHikariDataSource1 mysqlHikariDataSource1;

    @ApiOperation(value = "返回请求的页面",notes = "页面信息")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public String getList(ModelMap modelMap) {
        modelMap.put("deviceName", "设备状态");
        List<DeviceBean> deviceBeanList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            DeviceBean deviceBean = new DeviceBean();
            deviceBean.setDeviceStatus(i + "");
            deviceBeanList.add(deviceBean);
        }
        modelMap.put("deviceBeanList", deviceBeanList);
        return "device/list";
    }

    @ApiOperation(value = "请求设备数据",notes = "设备信息列表")
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public String getList(DeviceBean deviceBean) {
        //mybatis查询
        List<DeviceBean> list = deviceService.getByCondition(deviceBean);
        List<DeviceBean> list2 = deviceService.getByCondition2(deviceBean);
        list.addAll(list2);
        //jpa查询
        JpaDeviceBean jpaDeviceBean = new JpaDeviceBean();
        jpaDeviceBean.setDeviceStatus("1");
        List<JpaDeviceBean> list3 = deviceService.getByCondition(jpaDeviceBean);

        return JSON.toJSONString(list);
    }

    @RequestMapping(value = "/clearCache", method = RequestMethod.POST)
    @ResponseBody
    public String clearCache(DeviceBean deviceBean) {
        deviceService.clearCache(deviceBean);
        return JSON.toJSONString("success");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap modelMap) {
        return "device/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(DeviceBean deviceBean) {
        int num = deviceService.insertDevice(deviceBean);
        if (num > 0) {
            return JSON.toJSONString("success");
        } else {
            return JSON.toJSONString("fail");
        }
    }

    @RequestMapping(value = "/deleteData", method = RequestMethod.POST)
    @ResponseBody
    public String deleteData(DeviceBean deviceBean) {
        int num = deviceService.updateDevice(deviceBean);
        if (num > 0) {
            return JSON.toJSONString("success");
        } else {
            return JSON.toJSONString("fail");
        }
    }
}
