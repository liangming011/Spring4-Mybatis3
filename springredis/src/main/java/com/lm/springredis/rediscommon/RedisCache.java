//package com.lm.springredis.rediscommon;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.serializer.support.SerializingConverter;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.RedisSerializer;
//
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
///**
// * 实现缓存功能 * <p> * 通过jedisTemplate来实现将我们的数据以及数据结构保存到缓存redis中， <br> * 这个是由Spring封装的模板 * * @author zhangpengliang *
// */
//public class RedisCache implements Cache {
//    private RedisTemplate<String, Object> jedisTemplate;
//    @Value("${redis.host}")
//    private String redishost;
//
//    /**
//     * 按键值的方式存储 * * @param key * 键 * @param value * 值
//     */
//    public void put(String key, Object value) {
//        jedisTemplate.opsForValue().set(key, value);
//    }
//
//    @Override
//    public void put(String key, Object value, int timeout) {
//        jedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
//        TimeUnit.SECONDS
//    }
//
//    @Override
//    public Object get(String key) {
//        return jedisTemplate.opsForValue().get(key);
//    }
//
//    /**
//     * 获取key对应的值，并转换为指定类型 * * @param key * 键 * @param requiredType * 类型 * @return
//     */
//    @SuppressWarnings("unchecked")
//    public <T> T get(String key, Class<T> requiredType) {
//        return (T) jedisTemplate.opsForValue().get(key);
//    }
//
//    /**
//     * 移出 * * @param key
//     */
//    public void remove(String key) {
//        jedisTemplate.delete(key);
//    }
//
//    @Override
//    public Set<String> keys(String pattern) {
//        return jedisTemplate.keys(pattern);
//    }
//
//    @Override
//    public void putHash(String key, Map<?, ?> value) {
//        jedisTemplate.opsForHash().putAll(key, value);
//    }
//
//    /**
//     * 以Hash的方式将对象存入缓存中 * * @param key * 缓存中的key * @param hashKey * hash中的key * @param value * hash中的value
//     */
//    public void putHash(String key, String hashKey, Object value) {
//        jedisTemplate.opsForHash().put(key, hashKey, value);
//    }
//
//    @Override
//    public Map<?, Object> getHash(String key) {
//        return jedisTemplate.opsForHash().entries(key);
//    }
//
//    @Override
//    public Object getHash(String key, String hashKey) {
//        return jedisTemplate.opsForHash().get(key, hashKey);
//    }
//
//    @SuppressWarnings("unchecked")
//    @Override
//    public <T> T getHash(String key, String hashKey, Class<T> requiredType) {
//        return (T) jedisTemplate.opsForHash().get(key, hashKey);
//    }
//
//    @Override
//    public void removeHash(String key) {
//        jedisTemplate.opsForHash().delete(key);
//    }
//
//    @Override
//    public void removeHash(String key, Object... hashKeys) {
//        jedisTemplate.opsForHash().delete(key, hashKeys);
//    }
//
//    @Override
//    public void put(final Map<String, ?> data) {
//        RedisCallback<Map<String, ?>> pipCallBack = new RedisCallback<Map<String, ?>>()
//        @Override public Map<String, ?> doInRedis (RedisConnection connection) throws DataAccessException {
//            connection.openPipeline();
//            RedisSerializer<String> rs = jedisTemplate.getStringSerializer();
//            SerializingConverter sc = new SerializingConverter();
//            for (String key : data.keySet()) {
//                connection.append(rs.serialize(key), sc.convert(data.get(key)));
//            }
//            connection.closePipeline();
//            return null;
//        }
//    }
//
//    ; jedisTemplate.execute(pipCallBack);}
//
//    public boolean zAdd(String key, Object value) {
//        return this.zAdd(key, value, System.currentTimeMillis());
//    }
//
//    public boolean zAdd(String key, Object value, double score) {
//        return jedisTemplate.opsForZSet().add(key, value, score);
//    }
//
//    public Set<Object> zGetByRank(String key, long start, long end) {
//        return jedisTemplate.opsForZSet().range(key, start, end);
//    }
//
//    public Set<Object> zGetByScore(String key, double min, double max) {
//        return jedisTemplate.opsForZSet().rangeByScore(key, min, max);
//    }
//
//    public Long zRemove(String key, Object... values) {
//        return jedisTemplate.opsForZSet().remove(key, values);
//    }
//
//    public Long zRemoveByRank(String key, long start, long end) {
//        return jedisTemplate.opsForZSet().removeRange(key, start, end);
//    }
//
//    public Long zRemoveByScore(String key, double min, double max) {
//        return jedisTemplate.opsForZSet().removeRangeByScore(key, min, max);
//    }
//
//    public RedisTemplate<String, Object> getJedisTemplate() {
//        return jedisTemplate;
//    }
//
//    public void setJedisTemplate(RedisTemplate<String, Object> jedisTemplate) {
//        this.jedisTemplate = jedisTemplate;
//    }
//}