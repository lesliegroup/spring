package com.itleslie.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 * 原理:
 * 		CacheManager === Cache 缓存组件来实际给缓存中存取数据
 * 	  1）、引入redis的starter ,容器中保存的是 RedisCacheManager
 * 	  2)、RedisCacheManager 帮我们创建 RedisCache，RedisCache通过Redis操作缓存
 * 	  3)、默认保存数据 k -v 都是object ;利用序列化保存;如何自动保存json
 * 	  		1、引入了 redis 的 starter ，cacheManager 变为 RedisCacheManager
 * 	  		2、默认创建的 RedisCacheManager 操作 redis 的时候使用的是 RedisTemplate<Object,Object>
 * 	  		3、RedisTemplate<Object,Object> 是 默认使用jdk的序列化机制
 * 	  4）、自定义CacheManager
 *
 *
 */

@MapperScan("com.itleslie.cache.mapper")
@EnableCaching //开启缓存
@SpringBootApplication
public class SpringCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCacheApplication.class, args);
	}
}
