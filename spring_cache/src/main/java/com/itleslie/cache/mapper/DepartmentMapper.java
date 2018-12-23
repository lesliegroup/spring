package com.itleslie.cache.mapper;


import com.itleslie.cache.bean.Department;

/**
 * @author leslie
 * @create 2018-03-25
 * @Description
 **/
public interface DepartmentMapper {

    public Department selectById(Integer id);

    public void updateDep(Department department);

    Department getDepByDepartmentName(String departmentName);
}
