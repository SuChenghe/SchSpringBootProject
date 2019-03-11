package com.suchenghe.service;

/**
 * @author SuChenghe
 * @date 2018/12/4 13:54
 */
public interface RedisService {
    void putValue(String key, String value);

    String getKeyValue(String key);
}
