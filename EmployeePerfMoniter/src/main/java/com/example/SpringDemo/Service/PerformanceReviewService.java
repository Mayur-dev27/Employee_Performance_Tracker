package com.example.SpringDemo.Service;

import java.util.List;  
import com.example.SpringDemo.Entity.PerformanceReview;

public interface PerformanceReviewService {
	PerformanceReview addPerformanceReview(Long employeeId, PerformanceReview performanceReview);
    List<PerformanceReview> getReviewsByEmployee(Long employeeId);
    List<PerformanceReview> getAllReviews();
    PerformanceReview updatePerformanceReview(Long prId, PerformanceReview performanceReview);
    void deletePerformanceReview(Long prId);
}
