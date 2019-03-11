package com.suchenghe.dao.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @author SuChenghe
 * @date 2018/12/4 13:49
 */
@Component
public class RedisBaseDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisBaseDao.class);

    @Autowired
    JedisPool jedisPool;

    /**
     * 获取Jedis连接
     *
     * @return
     */
    private Jedis getJedis() {
        Jedis jedis = jedisPool.getResource();
        if (jedis == null) {
            LOGGER.error("Failed to get jedis object!");
        }
        return jedis;
    }


    /**
     * 向redis中存入值(key,value)
     */
    public void set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.set(key, value);
        } catch (Exception e) {
            LOGGER.error("Redis utils error:", e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 放入值并设置过期时间
     */
    public void setEx(String key, String value, int expire) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.setex(key, expire, value);
        } catch (Exception e) {
            LOGGER.error("Redis utils error:", e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 根据key获取对应的值
     */
    public String get(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = getJedis();
            value = jedis.get(key);
        } catch (Exception e) {
            LOGGER.error("Redis utils error:", e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 判断key是否存在
     */
    public boolean isExist(String key) {
        Jedis jedis = null;
        boolean exists = false;
        try {
            jedis = getJedis();
            exists = jedis.exists(key);
        } catch (Exception e) {
            LOGGER.error("Redis utils error:", e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return exists;
    }


    /**
     * 头部追加元素
     *
     * @param key
     * @param value
     */
    public void lpush(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.lpush(key, value);
        } catch (Exception e) {
            LOGGER.error("Redis utils error:", e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 尾部追加元素
     *
     * @param key
     * @param value
     */
    public void rpush(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.rpush(key, value);
        } catch (Exception e) {
            LOGGER.error("Redis utils error:", e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 获取列表头部元素(非阻塞)
     *
     * @param key
     */
    public String lpop(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = getJedis();
            value = jedis.lpop(key);
        } catch (Exception e) {
            LOGGER.error("Redis utils error:", e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 获取列表尾部元素(非阻塞)
     *
     * @param key
     */
    public String rpop(String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = getJedis();
            value = jedis.rpop(key);
        } catch (Exception e) {
            LOGGER.error("Redis utils error:", e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 获取队列的长度
     *
     * @param key
     */
    public Long llen(String key) {
        Jedis jedis = null;
        Long value = null;
        try {
            jedis = getJedis();
            value = jedis.llen(key);
        } catch (Exception e) {
            LOGGER.error("Redis utils error:", e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return value;
    }

    /**
     * 添加到set
     *
     * @param key
     * @param value
     */
    public void sadd(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.sadd(key, value);
        } catch (Exception e) {
            LOGGER.error("Redis utils error:", e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
    }

    /**
     * 遍历set集合
     *
     * @param key
     */
    public Set<String> smembers(String key) {
        Jedis jedis = null;
        Set<String> set = null;
        try {
            jedis = getJedis();
            set = jedis.smembers(key);
        } catch (Exception e) {
            LOGGER.error("Redis utils error:", e);
        } finally {
            if (null != jedis) {
                jedis.close();
            }
        }
        return set;
    }
}
