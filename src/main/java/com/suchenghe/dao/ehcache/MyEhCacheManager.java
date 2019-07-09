package com.suchenghe.dao.ehcache;

import lombok.Getter;
import lombok.Setter;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.time.Duration;

/**
 * .heap(10, EntryUnit.ENTRIES)
 * --define a resource pool for the heap. This will be your faster but smaller pool.
 * .offheap(1, MemoryUnit.MB)
 * --define a resource pool for the off-heap. Still pretty fast and a bit bigger
 * .disk(20, MemoryUnit.MB, true)
 * --define a persistent resource pool for the disk.
 *
 * @author SuChenghe
 * @date 2018/12/20 21:12
 */
//@Configuration
//@Getter
//@Setter
public class MyEhCacheManager implements InitializingBean, DisposableBean {

    /***
     * 缓存名称，注意不要起和类的bean名称冲突的名称
     */
    public static final String myFirstCacheName = "myFirstCacheName";
    protected final Logger LOGGER = LoggerFactory.getLogger(MyEhCacheManager.class);
    private CacheManager cacheManager;
    private Cache myFirstCache;

    @Override
    public void afterPropertiesSet() throws Exception {
        File cacheFile = new File("D:\\MyRunDataLog\\ehcache", "myFirstCache");

        cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(cacheFile)).build();
        cacheManager.init();
        LOGGER.info("Ehcache,CacheManager初始化成功");

        ResourcePoolsBuilder resourcePoolsBuilder = ResourcePoolsBuilder.newResourcePoolsBuilder()
                .heap(10, EntryUnit.ENTRIES)
                .offheap(1, MemoryUnit.MB)
                .disk(20, MemoryUnit.MB, true);

        CacheConfiguration<Long, String> cacheConfiguration
                = CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, resourcePoolsBuilder)
                //有效期
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(10)))
                .build();

        myFirstCache = cacheManager.createCache(myFirstCacheName, cacheConfiguration);
        LOGGER.info("Ehcache," + myFirstCacheName + "始化成功");

    }

    @Override
    public void destroy() throws Exception {
        //将Cache释放所有本地保存的瞬态资源（如内存）,持久化到磁盘中的不会移除
        cacheManager.removeCache(myFirstCacheName);
        cacheManager.close();
        LOGGER.info("Ehcache,CacheManager成功销毁");
    }

}
