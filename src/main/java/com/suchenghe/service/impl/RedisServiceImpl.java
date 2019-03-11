package com.suchenghe.service.impl;

import com.suchenghe.dao.redis.RedisBaseDao;
import com.suchenghe.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SuChenghe
 * @date 2018/12/4 13:55
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    RedisBaseDao redisBaseDao;

    @Override
    public void putValue(String key, String value) {
        redisBaseDao.set(key, value);
    }

    @Override
    public String getKeyValue(String key) {
        return redisBaseDao.get(key);
    }

}
