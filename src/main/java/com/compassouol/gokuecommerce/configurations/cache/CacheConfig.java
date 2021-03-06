package com.compassouol.gokuecommerce.configurations.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching
public class CacheConfig {
    @Autowired
    private Environment env;

    // caches
    public final static String cacheAddressesList = "addressesList";

    @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }

    @Bean
    public net.sf.ehcache.CacheManager ehCacheManager() {
        CacheConfiguration addressesList = new CacheConfiguration();
        addressesList.setName(cacheAddressesList);
        addressesList.setMemoryStoreEvictionPolicy("LRU");
        addressesList.setMaxEntriesLocalHeap(50);
        addressesList.setTimeToLiveSeconds(Long.parseLong(env.getProperty("cacheTimeInSeconds")));

        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        config.addCache(addressesList);
        return net.sf.ehcache.CacheManager.newInstance(config);
    }
}