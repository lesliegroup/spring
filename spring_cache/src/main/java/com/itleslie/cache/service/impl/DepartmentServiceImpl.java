package com.itleslie.cache.service.impl;

import com.itleslie.cache.bean.Department;
import com.itleslie.cache.mapper.DepartmentMapper;
import com.itleslie.cache.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @author leslie
 * @create 2018-03-25
 * @Description
 **/
@CacheConfig(cacheNames = "dep",cacheManager = "depCacheManager")  //使用CacheConfig 时 本方法内的 @Cacheable 无需指定 cacheNames
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 将方法的结果缓存:以后再要相同的数据，直接从缓存中获取，不用调用方法
     * 几个属性：
     *      cacheNames/value ：指定缓存名称
     *      key ：缓存数据中使用key 可以采用它来指定.默认是使用方法的参数的值
     *            编写 SpEl : #id;参数id的值 , #a0 #p0 #root.args[0]
     *      keyGenerator: key的生成器;可以采用自己指定key的生成器的主键id
     *          key/keyGenerator 二选一使用
     *      cacheManager : 指定缓存管理器.或者cacheResolver指定获取解析器
     *      condition:指定符合条件的情况下缓存
     *      unless:否定缓存,当unless指定的条件为true,方法的返回值就不会被缓存,可以获取到结果进行判断
     *            unless = "#result == null"
     *      sync : 是否使用异步模式
     *
     * 原理:
     *    1.自动配置类：CacheAutoConfiguration
     *    2.缓存的配置类
     *          org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
                org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
                org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
                org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
                org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
                org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
                org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
                org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
                org.springframework.boot.autoconfigure.cache.GuavaCacheConfiguration
                org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
                org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration
     *
     *    3.哪个配置类默认生效:
     *          org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
     *    4.给容器中注册了一个CacheManager: ConcurrentMapCacheManager
     *    5.可以获取和创建ConcurrentMapCache类型的缓存组件:它的作用的是将数据保存在ConcurrentMap中
     *
     * 运行流程:
     *    @Cacheable
     *    1.方法运行之前,先去查询cache(缓存组件),按照cacheNames指定名字获取
     *      (CacheManager先获取相应的缓存),第一次获取缓存如果没有Cache组件会自动创建。
     *    2.去Cache中查找缓存的内容,使用一个key，默认就是方法的参数.
     *      key是按照某种策略生成的：默认是使用keyGenerator生成的，默认使用SimpleKeyGenerator生成key
     *      SimpleKeyGenerator 生成key的默策略
     *                  如果没有参数： key = new SimpleKey();
     *                  如果有一个参数: key = 参数的值
     *                  如果有多个参数: key = new SimpleKey(params)
     *    3.没有查询到缓存就调用目标方法；
     *    4.将目标方法返回的结果,放进缓存中
     *
     *    @Cacheable 标注的方法执行之前先来检车缓存中有没有这个数据,默认按照参数的值作为key去查询缓存,
     *    如果没有就运行方法并将结果放入缓存，以后再来调用就可以直接使用缓存中的数据
     *
     * 核心:
     *    1.使用CacheManager【ConcurrentMapCacheManager】按照名字得的Cache【ConcurrentMapCache】 组件
     *    2.key使用KeyGenerator生成的,默认是SimpleKeyGenerator
     *
     *
     *
     **/
    @Cacheable(cacheNames = "dep1",key = "#id")  //cacheNames
    @Override
    public Department selectById(Integer id){
        return departmentMapper.selectById(id);
    }

  /*  @Cacheable(cacheNames = "dep",key = "#root.methodName+'['+#id+']'")  //key
    @Override
    public Department selectById(Integer id){
        return departmentMapper.selectById(id);
    }*/

   /* @Cacheable(cacheNames = "dep",keyGenerator = "myKeyGenerator")  //see com.itleslie.cache.config.MyCacheConfig.keyGenerator()
    @Override
    public Department selectById(Integer id){
        return departmentMapper.selectById(id);
    }*/


   /* @Cacheable(cacheNames = "dep",keyGenerator = "myKeyGenerator",condition = "#a0 > 1")  //当id>1时才进行缓存
    @Override
    public Department selectById(Integer id){
        return departmentMapper.selectById(id);
    }*/

   /* @Cacheable(cacheNames = "dep",keyGenerator = "myKeyGenerator",unless = "#a0 == 1")  //当id == 1时不缓存
    @Override
    public Department selectById(Integer id){
        return departmentMapper.selectById(id);
    }*/




    /**
     * @CachePut: 及调用方法,又更新缓存数据
     * 修改了数据库的某个数据,同时更新缓存
     * 运行时机：
     *      1.先调用目标方法
     *      2.将目标方法的结果缓存起来
     * 测试步骤:
     *  1、查询ID为1的部门；查询的结果会放在缓存中；
     *
     *  2、以后查询还是之前的结果
     *
     *  3、更新ID为1的部门时，需要设置key为部门Id
     *
     *  4、查询1号员工
     *      应该是更新后的员工
     *       key = "#department.id",使用传入参数的id,
     *       key = "#result.id",使用返回后的id
     *       @Cacheable的key不能用#result
     */
    @CachePut(/*cacheNames = "dep",*/key = "#result.id")
    @Override
    public Department updateDep(Department department) {
         departmentMapper.updateDep(department);
         return department;
    }


    /***
     *@CacheEvict: 缓存清楚
     *  key:指定要清除的数据
     *  allEntries : 删除缓存所有数据
     *  beforeInvocation = false,  缓存的清除是否在方法前之前执行
     *               默认代表缓存清除操作是在方法执行后执行;如果出现异常缓存就不会清除
     *
     *  beforeInvocation = true
     *       代表清除缓存操作是在方法运行之前执行,无论方法是否出现异常,缓存都清除
     *
     *
     */
    @CacheEvict(/*cacheNames = "dep",*/allEntries = true,beforeInvocation = true)
    @Override
    public void delete(Integer id) {
        System.out.println("delete Department:"+id);
        int i = 10/0;
    }


    @Caching(
            cacheable = {
                    @Cacheable(key = "#departmentName")
            },
            put = {
                    @CachePut(key = "#result.id"), //使用返回结果的id作为key存入缓存
//                    @CachePut(value = "dep",key = "#result.departmentName") //使用返回结果的部门名称作为key存入缓存

//                    PS：使用 @CachePut 注解时一定会查数据库
            }
    )
    @Override
    public Department getDepByDepartmentName(String departmentName){
        return departmentMapper.getDepByDepartmentName(departmentName);
    }

}
