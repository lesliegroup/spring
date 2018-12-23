package com.itleslie.cache.service;

import com.itleslie.cache.bean.Employee;

/**
 * @author leslie
 * @create 2018-03-25
 * @Description
 **/
public interface EmployeeService {

    Employee selectById(Integer id);
}
