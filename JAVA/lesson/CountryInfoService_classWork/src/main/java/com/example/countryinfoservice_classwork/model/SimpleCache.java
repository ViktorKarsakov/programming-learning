package com.example.countryinfoservice_classwork.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleCache {
    Map<String, CacheEntry> cache = new ConcurrentHashMap<>();
    private long timeToLive;

    public SimpleCache(long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public class CacheEntry{
        Object value;
        long savedAtMs;

        public CacheEntry (Object value){
            this.value = value;
            this.savedAtMs = System.currentTimeMillis();
        }

        boolean isExpired(){
            return (System.currentTimeMillis() - savedAtMs) > timeToLive;
        }
    }
    public void put(String key, Object value){
        cache.put(key.toLowerCase(), new CacheEntry(value));
    }

    public Object get(String key){
        CacheEntry entry = cache.get(key.toLowerCase());
        if (entry == null){
            return null;
        }
        if (entry.isExpired()){
            cache.remove(key.toLowerCase());
            return null;
        }
        return entry.value;
    }

    public Map<String, Object> getStats() {
        return Map.of (
                "size", cache.size(),
                "ttlMillis", timeToLive
        );
    }

    public void clear() {
        cache.clear();
    }
}
