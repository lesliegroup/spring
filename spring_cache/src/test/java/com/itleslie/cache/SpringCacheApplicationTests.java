package com.itleslie.cache;

import com.itleslie.cache.bean.Department;
import com.itleslie.cache.mapper.DepartmentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringCacheApplicationTests {

	@Autowired
	private DepartmentMapper  departmentMapper;

	@Autowired
	StringRedisTemplate StringRedisTemplate;  //操作字符串

	@Autowired
	RedisTemplate redisTemplate; //k-V 都是对象的

	@Autowired
	RedisTemplate<Object,Department> departmentRedisTemplate;

	/**
	 * @method   : test01
	 * @describe :  String（字符串） list（列表） Set（集合） hash（散列） ZSet（有序集合）
	 * 				stringRedisTemplate.opsForValue() 【字符串】
	 * 				stringRedisTemplate.opsForList() 【列表】
	 * 				stringRedisTemplate.opsForSet() (集合)
	 *
	 * @param    : []
	 * @author   : itleslie@leslie.com
	 * @date     : 2018/7/9
	 **/
	@Test
	public void test01(){
		StringRedisTemplate.opsForValue().set("key01","hello");
//		stringRedisTemplate.opsForSet()
		String msg = StringRedisTemplate.opsForValue().get("key01");
		System.out.println(msg);


	}

	@Test
	public void test02(){
		Department department = departmentMapper.selectById(1);
		redisTemplate.opsForValue().set("dep-01",department);
	}

	@Test
	public void test03(){
		Department department = departmentMapper.selectById(1);
		departmentRedisTemplate.opsForValue().set("dep",department);
	}


	@Test
	public void contextLoads() {

		Department department = departmentMapper.selectById(1);
		System.out.println(department);

	}

}
