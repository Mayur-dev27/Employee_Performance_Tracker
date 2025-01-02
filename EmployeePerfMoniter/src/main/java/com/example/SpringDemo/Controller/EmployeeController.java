package com.example.SpringDemo.Controller;

//import java.time.LocalDate;
//import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringDemo.Entity.Employee;
import com.example.SpringDemo.Entity.PerformanceReview;
import com.example.SpringDemo.Service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/")
    public String home() {
        // Simple response without Thymeleaf
        return "example";
    }

    @PostMapping("/createEmp")
    public Employee createEmployee(@RequestBody Employee employee) {
        // Check if performance reviews exist in the request
        if (employee.getReviews() != null && !employee.getReviews().isEmpty()) {
            // Link each review to the employee before saving
            for (PerformanceReview review : employee.getReviews()) {
                review.setEmployee(employee);
            }
        }
        // Save employee and cascade to reviews
        return employeeService.addEmployee(employee);
    }


    @GetMapping("/getEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

//    @PutMapping("/{id}")
//    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
//        return employeeService.updateEmployee(id, employee);
//    }
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        // Fetch the existing employee
        Employee existingEmployee = employeeService.getEmployeeById(id);

        // Update employee details
        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setDesignation(updatedEmployee.getDesignation());

        // Update reviews
        if (updatedEmployee.getReviews() != null) {
            // Clear existing reviews and reattach updated reviews
            existingEmployee.getReviews().clear();
            for (PerformanceReview review : updatedEmployee.getReviews()) {
                review.setEmployee(existingEmployee); // Set relationship
                existingEmployee.getReviews().add(review); // Add to employee
            }
        }

        // Save and return updated employee
        return employeeService.addEmployee(existingEmployee);
    }



    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "Employee deleted with id: " + id;
    }
}