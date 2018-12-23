package com.itleslie.cache.service.impl;

import com.itleslie.cache.bean.Employee;
import com.itleslie.cache.mapper.EmployeeMapper;
import com.itleslie.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

/**
 * @author leslie
 * @create 2018-03-25
 * @Description
 **/
/*@CacheConfig(cacheNames = "emp",cacheManager = "empRedisCacheManager")*/
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Qualifier("empRedisCacheManager")
    @Autowired
    private RedisCacheManager empRedisCacheManager;

    /**
     * @method   : selectById
     * @describe :
     *
     *            缓存的数据能存入redis；
     *            第二次从缓存中查询就不能反序列化回来；
     *            存的是emp的json数据;CacheManager 默认使用 RedisTemplate<Object,Department> 操作redis
     *
     *
     * @param    : [id]
     * @author   : itleslie@leslie.com
     * @date     : 2018/7/10
     **/

//    @Cacheable(key = "#id")
//    @Override
//    public Employee selectById(Integer id) {
//        return employeeMapper.selectById(id);
//    }

    //使用缓存管理器得到缓存,进行API调用
    @Override
    public Employee selectById(Integer id) {
        Employee employee = employeeMapper.selectById(id);
        // 获取某个缓存
        Cache emp = empRedisCacheManager.getCache("emp");
        emp.put("emp:1",employee);
        return employee;
    }
}
