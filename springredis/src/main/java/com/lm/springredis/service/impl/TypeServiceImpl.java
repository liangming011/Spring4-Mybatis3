package com.lm.springredis.service.impl;

import com.lm.springredis.dao.TypeDao;
import com.lm.springredis.entity.Type;
import com.lm.springredis.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * TypeServiceImpl
 *
 * 缓存机制说明：所有的查询结果都放进了缓存，也就是把MySQL查询的结果放到了redis中去，
 * 然后第二次发起该条查询时就可以从redis中去读取查询的结果，从而不与MySQL交互，从而达到优化的效果，
 * redis的查询速度之于MySQL的查询速度相当于 内存读写速度 /硬盘读写速度
 * @Cacheable("a")注解的意义就是把该方法的查询结果放到redis中去，下一次再发起查询就去redis中去取，存在redis中的数据的key就是a；
 * @CacheEvict(value={"a","b"},allEntries=true) 的意思就是执行该方法后要清除redis中key名称为a,b的数据；
 */
@Service
@CacheConfig(cacheNames = "TypeServiceImpl")
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @Cacheable  spring 会在其被调用后将返回值缓存起来，以保证下次利用同样的参数来执行该方法时
     * 可以直接从缓存中获取结果，而不需要再次执行该方法。
     * */
    @Override
    @Transactional(readOnly = true)//打开事务
    @Cacheable(key = "#type.idString")
    public Type selectType(Type type) throws Exception {

        ValueOperations<String, String> stringOperations = redisTemplate.opsForValue();
        // String类型数据存储，不设置过期时间，永久性保存
        stringOperations.set("string1", "fiala");
        System.out.println(stringOperations.get("string1"));
        // String类型数据存储，设置过期时间为80秒，采用TimeUnit控制时间单位
        stringOperations.set("string2", "fiala", 80, TimeUnit.SECONDS);
        // 判断key值是否存在，存在则不存储，不存在则存储
        stringOperations.setIfAbsent("string1", "my fiala");
        stringOperations.setIfAbsent("string3", "my fiala");
        String value1 = stringOperations.get("string1");
        String value2 = stringOperations.get("string3");
        System.out.println(value1);
        System.out.println(value2);
        return typeDao.selectType(type);
    }

    /**
     * @CacheEvict 用来标注在需要清除缓存元素的方法或类上的。
     * 当然这些注解里面还有很多其他的属性配置，配合 spring-el 表达式能做的事情还有很多，大概只有你想不到，没有做不到。
     * */
    @Transactional
    @Override
    @CacheEvict(key = "#type.idString")
    public boolean deleteType(Type type) throws Exception {
        return typeDao.deleteType(type);
    }

    /**
     * @CachePut  标注的方法在执行前不会去检查缓存中是否
     * 存在之前执行过的结果，而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中。
     * */
    @Override
    @Transactional
    @CachePut(key = "#type.idString")
    public boolean updateType(Type type) throws Exception {
        return typeDao.updateType(type);
    }

    @Override
    @Transactional
    @CachePut(key = "#type.idString")
    public boolean insertType(Type type) throws Exception {
        return typeDao.insertType(type);
    }

}
