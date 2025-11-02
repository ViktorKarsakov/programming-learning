package com.example.demo.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleCache {
    private Map<String, CacheData> cache = new ConcurrentHashMap<>();
    private long timeToLive;//1 cas

    public SimpleCache(long timeToLive) {
        this.timeToLive = timeToLive;
    }

    class  CacheData{
        MovieApiResponse data;
        long time;

        public CacheData(MovieApiResponse data) {
            this.data = data;
            this.time = System.currentTimeMillis();
        }

        boolean isExpired(){
            return (System.currentTimeMillis() - this.time) > timeToLive;
        }
    }


    public void put(String key, MovieApiResponse data){
        cache.put(key.toLowerCase(), new CacheData(data));
    }


    public MovieApiResponse get(String key){
        CacheData cacheData = cache.get(key.toLowerCase());

        if(cacheData == null){
            return null;
        }

        if(cacheData.isExpired()){
            cache.remove(key.toLowerCase());
            return null;
        }

        return cacheData.data;
    }

    public void clear(){
        cache.clear();
    }

    public  int size(){
        return cache.size();
    }
}
