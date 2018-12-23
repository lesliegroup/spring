package com.itleslie.cache.handler;

import com.itleslie.cache.bean.Department;
import com.itleslie.cache.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leslie
 * @create 2018-03-25
 * @Description
 **/
@RestController
@RequestMapping("/department")
public class DepartmentHandler {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/select/{id}")
    public Department selectById(@PathVariable("id") Integer id){
        return  departmentService.selectById(id);
    }

    @GetMapping("/update")
    public Department updateDep(Department department){
        return departmentService.updateDep(department);
    }

    @GetMapping("/delete/{id}")
    public String deleteDep(@PathVariable("id") Integer id){
        departmentService.delete(id);
        return "Success";
    }

    @GetMapping("/selectByDepartmentName/{departmentName}")
    public Department selectByDepartmentName(@PathVariable("departmentName") String departmentName){
        Department department = departmentService.getDepByDepartmentName(departmentName);
        System.out.println(department);
        return department;
    }
}
