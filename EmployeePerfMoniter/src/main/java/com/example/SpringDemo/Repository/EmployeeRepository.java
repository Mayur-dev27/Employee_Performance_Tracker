package com.example.SpringDemo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringDemo.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByName(String name);
}
