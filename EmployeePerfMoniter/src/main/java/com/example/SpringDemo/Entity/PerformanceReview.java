package com.example.SpringDemo.Entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PerformanceReview {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long prId;
	
    private String feedback;
    
    private double score;
    
    private LocalDate reviewDate;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "employee_id", nullable = false) // Foreign key column
    @JsonIgnore // Prevent recursive serialization
    private Employee employee;
    
    

	public PerformanceReview() {
	}

	public PerformanceReview(String feedback, double score, LocalDate reviewDate) {
		this.feedback = feedback;
		this.score = score;
		this.reviewDate = reviewDate;
	}

	public Long getPrId() {
		return prId;
	}

	public void setPrId(Long prId) {
		this.prId = prId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public LocalDate getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
    
    
    
}
