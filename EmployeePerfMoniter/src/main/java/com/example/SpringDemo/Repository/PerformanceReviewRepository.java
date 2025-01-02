package com.example.SpringDemo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringDemo.Entity.PerformanceReview;

public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, Long>{
	List<PerformanceReview> findByEmployeeId(Long employeeId);
}
