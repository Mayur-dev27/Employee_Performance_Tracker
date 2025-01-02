package com.example.SpringDemo.Controller;

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

import com.example.SpringDemo.Entity.PerformanceReview;
import com.example.SpringDemo.Service.PerformanceReviewService;

@RestController
@RequestMapping("/api/reviews")
public class PerformanceReviewController {

    @Autowired
    private PerformanceReviewService performanceReviewService;

    @PostMapping("/{employeeId}")
    public PerformanceReview createPerformanceReview(
            @PathVariable Long employeeId, 
            @RequestBody PerformanceReview performanceReview) {
        return performanceReviewService.addPerformanceReview(employeeId, performanceReview);
    }


    @GetMapping("/{employeeId}")
    public List<PerformanceReview> getReviewsByEmployee(@PathVariable Long employeeId) {
        return performanceReviewService.getReviewsByEmployee(employeeId);
    }

    @GetMapping("/getAllReviews")
    public List<PerformanceReview> getAllPerformanceReviews() {
        return performanceReviewService.getAllReviews();
    }

    @PutMapping("/{prId}")
    public PerformanceReview updatePerformanceReview(@PathVariable Long prId, @RequestBody PerformanceReview performanceReview) {
        return performanceReviewService.updatePerformanceReview(prId, performanceReview);
    }

    @DeleteMapping("/{prId}")
    public String deletePerformanceReview(@PathVariable Long prId) {
        performanceReviewService.deletePerformanceReview(prId);
        return "Review deleted with id: " + prId;
    }
}