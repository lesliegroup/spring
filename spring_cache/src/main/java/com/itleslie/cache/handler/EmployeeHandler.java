package com.itleslie.cache.handler;

import com.itleslie.cache.bean.Employee;
import com.itleslie.cache.service.DepartmentService;
import com.itleslie.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leslie
 * @create 2018-07-10
 * @Description
 **/

@RestController
@RequestMapping("/employee")
public class EmployeeHandler {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/select/{id}")
    public Employee findById(@PathVariable("id") Integer id){
        return employeeService.selectById(id);
    }

}
