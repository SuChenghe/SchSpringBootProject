package com.suchenghe.service;

import com.suchenghe.dao.mysql.pojo.DeviceBean;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * cache名称:
 * 当我们需要缓存的地方越来越多，你可以使用@CacheConfig(cacheNames = {"myCache"})注解来统一指定value的值，
 * 这时可在方法上省略value，如果在方法依旧写上了value，那么依然以方法的value值为准。
 * <p>
 * cache Key
 * 当我们要使用root对象的属性作为key时我们也可以将“#root”省略，因为Spring默认使用的就是root对象的属性。 如
 * targetClass:当前被调用的目标对象的类 ,methodName:方法名称，#p参数index，#p0第一个参数值索引
 *
 * @author SuChenghe
 * @Cacheable(key = "targetClass + methodName +#p0")
 * @date 2018/11/27 17:26
 */
public interface DeviceBeanService {

    int insertDevice(DeviceBean deviceBean);

    int updateDevice(DeviceBean deviceBean);

    //@Cacheable(key = "targetClass + methodName +#p0.deviceStatus")
    @Cacheable(value = "device_cache", key = "#deviceBean.deviceStatus")
    List<DeviceBean> getByCondition(DeviceBean deviceBean);

    List<DeviceBean> getByCondition2(DeviceBean deviceBean);

    List<DeviceBean> getByIdList(List<Long> idList);

    //beforeInvocation:是否在方法执行前就清空,缺省为 false
    @CacheEvict(value = "device_cache", key = "#deviceBean.deviceStatus")
    void clearCache(DeviceBean deviceBean);

}
