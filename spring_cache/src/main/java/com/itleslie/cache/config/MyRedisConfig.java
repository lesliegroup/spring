package com.itleslie.cache.config;

import com.itleslie.cache.bean.Department;
import com.itleslie.cache.bean.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 * @author leslie
 * @create 2018-07-10
 * @Description
 **/
@Configuration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<Object, Department> departmentRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Department> template = new RedisTemplate<Object, Department>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Department.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    // CacheManagerCustomizers 可以来定制缓存的一些规则

    /**
     *
     * No CacheResolver specified, and no unique bean of type CacheManager found. Mark one as primary
     * (or give it the name 'cacheManager') or declare a specific CacheManager to use,
     * that serves as the default one.
     *
     *
     *
     **/
    @Primary //使用当前 CacheManager 作为默认 CacheManager
    @Bean
    public RedisCacheManager depCacheManager(RedisTemplate<Object, Department> departmentRedisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(departmentRedisTemplate);
        //key多了一个前缀

        //使用前缀,默认会将CacheName作为key的前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }


    @Bean
    public RedisTemplate<Object, Employee> employeeRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Employee> template = new RedisTemplate<Object, Employee>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Employee.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    // CacheManagerCustomizers 可以来定制缓存的一些规则
    @Bean
    public RedisCacheManager empRedisCacheManager(RedisTemplate<Object, Employee> employeeRedisTemplate){
        RedisCacheManager cacheManager = new RedisCacheManager(employeeRedisTemplate);
        //key多了一个前缀

        //使用前缀,默认会将CacheName作为key的前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

}
