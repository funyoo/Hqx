package com.funyoo.hqxApp.util.cachePool;

/**
 * 缓存池工具
 */
public class CachePoolManager implements CachePoolInterface<String, String> {

    private static final CachePoolInterface<String, String> cacheLevel1 = CachePool.instance;
    private static final CachePoolInterface<String, String> cacheLevel2 = null;

    @Override
    public String get(String key) {
        String result = null;
        result = cacheLevel1.get(key);
        if (result == null) {
            result = cacheLevel2.get(key);
        }
        return result;
    }

    @Override
    public void put(String key, String value) {
        cacheLevel1.put(key, value);
        cacheLevel2.put(key, value);
    }

    @Override
    public String remove(String key) {
        String result = null;
        result = cacheLevel1.remove(key);
        cacheLevel2.remove(key);
        return result;
    }

    @Override
    public void clear() {
        cacheLevel1.clear();
        cacheLevel2.clear();
    }
}
