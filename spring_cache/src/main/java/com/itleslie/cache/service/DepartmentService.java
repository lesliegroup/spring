package com.itleslie.cache.service;


import com.itleslie.cache.bean.Department;

/**
 * @author leslie
 * @create 2018-03-25
 * @Description
 **/
public interface DepartmentService {


    Department selectById(Integer id);

    Department updateDep(Department department);

    void delete(Integer id);

    public Department getDepByDepartmentName(String departmentName);
}
