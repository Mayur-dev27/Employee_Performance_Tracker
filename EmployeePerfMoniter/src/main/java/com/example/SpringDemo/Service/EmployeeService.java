package com.example.SpringDemo.Service;

import java.util.List;

import com.example.SpringDemo.Entity.Employee;

public interface EmployeeService {

	Employee addEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Employee updateEmployee(Long id, Employee employeeDetails);
    void deleteEmployee(Long id);
}
