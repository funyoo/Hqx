package com.funyoo.hqxApp.util.cachePool;

import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CachePool implements CachePoolInterface<String, String> {

    public static final CachePool instance = new CachePool();
    private Map<String, String> cache;


    private CachePool() {
        cache = new ConcurrentHashMap<>();
    }

    @Override
    public String get(String s) {
        return cache.get(s);
    }

    @Override
    public void put(String key, String value) {
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
}
