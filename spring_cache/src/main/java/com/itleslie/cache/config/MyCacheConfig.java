package com.itleslie.cache.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author leslie
 * @create 2018-07-03
 * @Description
 **/
@Configuration
public class MyCacheConfig {

    /**
     * @method   : keyGenerator
     * @describe : 自定义key生成器
     * @param    : []
     * @author   : itleslie@leslie.com
     * @date     : 2018/7/3
     **/
    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator(){
        return new KeyGenerator(){
            @Override
            public Object generate(Object o, Method method, Object... params) {
                return method.getName()+"["+ Arrays.toString(params).toString()+"]";
            }
        };
    }
}
