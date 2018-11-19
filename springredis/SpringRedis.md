###Spring 缓存 体系概述  

SpringBoot 缓存
在 Spring Boot中，通过@EnableCaching注解自动化配置合适的缓存管理器（CacheManager），Spring Boot根据下面的顺序去侦测缓存提供者： 
* Generic 
* JCache (JSR-107) 
* EhCache 2.x 
* Hazelcast 
* Infinispan 
* Redis 
* Guava 
* Simple

关于 Spring Boot 的缓存机制： 
高速缓存抽象不提供实际存储，并且依赖于由org.springframework.cache.Cache和org.springframework.cache.CacheManager接口实现的抽象。 Spring Boot根据实现自动配置合适的CacheManager，只要缓存支持通过@EnableCaching注释启用即可。

##本次使用的是 Redis 缓存

Spring集成Redis的几个步骤：  
1、搭建Redis 服务器环境（windows环境下搭建\Linux环境下搭建）  
2、启动Redis服务  
3、maven项目中添加依赖   
4、配置Spring对Redis相关bean的引用   
5、配置Redis基本属性  
6、编辑实现RedisTemplate的实现工具类  
7、遇到的问题  