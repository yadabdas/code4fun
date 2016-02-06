package com.code4fun.datastructure.cache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yadab on 2/7/2016.
 */
public class LRUCacheTest {
    private LRUCache<String, String> lruCache;

    @Before
    public void setup(){
        lruCache = new LRUCache<>(10);
    }

    @Test
    public void testGet() throws Exception {
        for(int i=0;i<10;i++){
            lruCache.put(String.valueOf(i),String.valueOf(i));
        }
        Assert.assertTrue(lruCache.get(String.valueOf(0)).equals(String.valueOf(0)));
    }

    @Test
    public void testPut() throws Exception {
        for(int i=0;i<10;i++){
            lruCache.put(String.valueOf(i),String.valueOf(i));
        }
        Assert.assertTrue(lruCache.get(String.valueOf(0)).equals(String.valueOf(0)));
    }
    @Test
    public void testNonRecentAccessGetsRemoved() throws Exception {
        for(int i=0;i<10;i++){
            lruCache.put(String.valueOf(i),String.valueOf(i));
        }
        for(int i=0;i<5;i++){
            lruCache.get(String.valueOf(i));
        }
        for(int i=10;i<15;i++){
            lruCache.put(String.valueOf(i), String.valueOf(i));
        }
        for(int i=0;i<5;i++){
            Assert.assertTrue(lruCache.get(String.valueOf(i)).equals(String.valueOf(i)));
        }
    }
}