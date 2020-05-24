package com.funyoo.hqxApp.util.cachePool;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CachePool implements CachePoolInterface<String, String> {

    public static final CachePool instance = new CachePool();
    private Map<String, String> cache;
    // 时间戳
    private long timeStamp;
    // 清理时间间隔
    private long timeClean;

    private CachePool() {
        cache = new ConcurrentHashMap<>();
        timeStamp = System.currentTimeMillis();
        timeClean = 6 * 60 * 60 * 1000;
    }

    @Override
    public String get(String s) {
        if (System.currentTimeMillis() - timeStamp > timeClean) {
            clear();
        }
        return cache.get(s);
    }

    @Override
    public void put(String key, String value) {
        if (cache.size() > 1 << 20) {
            cache.clear();
        }
        cache.put(key, value);
    }

    @Override
    public String remove(String key) {
        return cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    public void setTimeClean(Long clean) {
        timeClean = clean;
    }
}
