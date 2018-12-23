package com.itleslie.cache.bean;

import java.io.Serializable;

/**
 * @author leslie
 * @create 2018-03-25
 * @Description
 **/
public class Department implements Serializable{
    private static final long serialVersionUID = 6084574712607231002L;

    private Integer id;
    private String departmentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}

