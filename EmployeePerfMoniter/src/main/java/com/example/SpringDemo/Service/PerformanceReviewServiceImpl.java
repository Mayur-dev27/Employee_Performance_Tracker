package com.example.SpringDemo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SpringDemo.Entity.Employee;
import com.example.SpringDemo.Entity.PerformanceReview;
import com.example.SpringDemo.Repository.EmployeeRepository;
import com.example.SpringDemo.Repository.PerformanceReviewRepository;

@Service
public class PerformanceReviewServiceImpl implements PerformanceReviewService {

    @Autowired
    private PerformanceReviewRepository performanceReviewRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository; // Add this dependency

    @Override
    public PerformanceReview addPerformanceReview(Long employeeId, PerformanceReview performanceReview) {
        // Fetch the employee using the employee ID
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        
        // Set the employee for the review
        performanceReview.setEmployee(employee);

        // Save and return the performance review
        return performanceReviewRepository.save(performanceReview);
    }


    @Override
    public List<PerformanceReview> getReviewsByEmployee(Long employeeId) {
        return performanceReviewRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<PerformanceReview> getAllReviews() {
        return performanceReviewRepository.findAll();
    }

    @Override
    public PerformanceReview updatePerformanceReview(Long prId, PerformanceReview performanceReview) {
        PerformanceReview existingReview = performanceReviewRepository.findById(prId)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + prId));
        existingReview.setFeedback(performanceReview.getFeedback());
        existingReview.setScore(performanceReview.getScore());
        existingReview.setReviewDate(performanceReview.getReviewDate());
        return performanceReviewRepository.save(existingReview);
    }

    @Override
    public void deletePerformanceReview(Long prId) {
        PerformanceReview review = performanceReviewRepository.findById(prId)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + prId));
        performanceReviewRepository.delete(review);
    }
}
