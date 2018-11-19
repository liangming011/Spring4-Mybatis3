//package com.lm.springredis.rediscommon;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * 缓存管理工具 * @author zhangpengliang *
// */
//public class CacheManage {
//
//    private static Cache cache;
//
//    public static Cache getCache() {
//        return cache;
//    }
//
//    @Autowired
//    public static void setCache(Cache cache) {
//        CacheManage.cache = cache;
//    }
//    /*public CacheManage(Cache cache){ CacheManage.cache=cache; }*/
//
//    /**
//     * 按键值的方式存储 * @param key * @param value
//     */
//    public static void put(String key, Object value) {
//        CacheManage.cache.put(key, value);
//    }
//
//    /**
//     * 按键值方式存储，可以设置超时 * * @param key * 键 * @param value * 值 * @param timeout * 超时时间
//     */
//    public static void put(String key, Object value, int timeout) {
//        CacheManage.cache.put(key, value, timeout);
//    }
//
//    /**
//     * 获取key对应的值 * * @param key * 键 * @return
//     */
//    public static Object get(String key) {
//        return CacheManage.cache.get(key);
//    }
//
//    /**
//     * 获取key对应的值，并转换为指定类型 * * @param key * 键 * @param requiredType * 类型 * @return
//     */
//    public static <T> T get(String key, Class<T> requiredType) {
//        return CacheManage.cache.get(key, requiredType);
//    }
//
//    /**
//     * 移除对应值 * * @param key * 键
//     */
//    public static void remove(String key) {
//        CacheManage.cache.remove(key);
//    } //为手动
//
//    public static Set<String> keys(String pattern) {
//        return CacheManage.cache.keys(pattern);
//    }
//
//    public static void putHash(String key, Map<?, ?> value) {
//        CacheManage.cache.putHash(key, value);
//    }
//
//    public static void putHash(String key, String hashKey, Object value) {
//        CacheManage.cache.putHash(key, hashKey, value);
//    }
//
//    public static Map<?, ?> getHash(String key) {
//        return CacheManage.cache.getHash(key);
//    }
//
//    public static Object getHash(String key, String hashKey) {
//        return CacheManage.cache.getHash(key, hashKey);
//    }
//
//    public static <T> T getHash(String key, String hashKey, Class<T> requiredType) {
//        return CacheManage.cache.getHash(key, hashKey, requiredType);
//    }
//
//    public static void removeHash(String key) {
//        CacheManage.cache.removeHash(key);
//    }
//
//    public static void removeHash(String key, Object... hashKeys) {
//        CacheManage.cache.removeHash(key, hashKeys);
//    }
//
//    /**
//     * 使用pipeline的方式插入批量数据 * <p> * 使用get相关方法获取，请勿使用getHash方法取值 * * @param data * 需要被插入的批量数据
//     */
//    public static void put(Map<String, ?> data) {
//        CacheManage.cache.put(data);
//    }
//
//    public boolean zAdd(String key, Object value) {
//        return CacheManage.cache.zAdd(key, value);
//    }
//
//    public boolean zAdd(String key, Object value, double score) {
//        return CacheManage.cache.zAdd(key, value, score);
//    }
//
//    public Set<Object> zGetByRank(String key, long start, long end) {
//        return CacheManage.cache.zGetByRank(key, start, end);
//    }
//
//    public Set<Object> zGetByScore(String key, double min, double max) {
//        return CacheManage.cache.zGetByScore(key, min, max);
//    }
//
//    public Long zRemove(String key, Object... values) {
//        return CacheManage.cache.zRemove(key, values);
//    }
//
//    public Long zRemoveByRank(String key, long start, long end) {
//        return CacheManage.cache.zRemoveByRank(key, start, end);
//    }
//
//    public Long zRemoveByScore(String key, double min, double max) {
//        return CacheManage.cache.zRemoveByScore(key, min, max);
//    }
//}