package com.example.resttempalte_classwork.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Scope("singleton")
public class SimpleCache {
    private Map<String, CacheEntry> cache = new ConcurrentHashMap<>();
    private long timeToLive = 60 * 1000;


    class CacheEntry {
        Object value;
        long savedAtMs;

        public CacheEntry(Object value) {
            this.value = value;
            this.savedAtMs = System.currentTimeMillis();
        }

        boolean isExpired() {
            return (System.currentTimeMillis() - savedAtMs) > timeToLive;
        }
    }

    public void put(String key, Object value) {
        cache.put(key.toLowerCase(), new CacheEntry(value));
    }

    public Object get(String key) {
        CacheEntry entry = cache.get(key.toLowerCase());

        if (entry == null) {
            return null;
        }

        if (entry.isExpired()) {
            cache.remove(key.toLowerCase());
            return null;
        }
        return entry.value;
    }
    
}
