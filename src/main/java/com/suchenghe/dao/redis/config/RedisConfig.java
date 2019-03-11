package com.suchenghe.dao.redis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author SuChenghe
 * @date 2018/12/4 13:37
 */
@Component
@PropertySource("classpath:jdbc/redis.properties")
public class RedisConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${redis.address}")
    private String address;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.password}")
    private String password;
    @Value("${redis.maxTotal}")
    private int maxTotal;
    @Value("${redis.maxIdle}")
    private int maxIdle;
    @Value("${redis.timeout}")
    private int timeout;
    @Value("${redis.database}")
    private int database;

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        //最大空闲连接数
        config.setMaxIdle(maxIdle);
        //最大连接数
        config.setMaxTotal(maxTotal);
        //获取连接时的最大等待毫秒数
        config.setMaxWaitMillis(20000);
        //在获取连接的时候检查有效性, 默认false
        config.setTestOnBorrow(true);
        //调用returnObject方法时，是否进行有效检查
        config.setTestOnReturn(true);

        JedisPool jedisPool = null;
        try {
            if (null == password || password.isEmpty()) {
                jedisPool = new JedisPool(config, address, port, timeout);
            } else {
                jedisPool = new JedisPool(config, address, port, timeout, password, database);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to init jedis pool!");
        }
        return jedisPool;
    }


}
