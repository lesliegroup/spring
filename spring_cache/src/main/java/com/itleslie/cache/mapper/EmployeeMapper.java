package com.itleslie.cache.mapper;

import com.itleslie.cache.bean.Employee;

/**
 * @author leslie
 * @create 2018-03-25
 * @Description
 **/
public interface EmployeeMapper {

    Employee selectById(Integer id);
}
