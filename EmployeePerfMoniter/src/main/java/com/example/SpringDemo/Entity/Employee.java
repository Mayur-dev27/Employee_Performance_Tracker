package com.example.SpringDemo.Entity;

import java.util.ArrayList;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String designation;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<PerformanceReview> reviews = new ArrayList<>();
	
	public Employee() {
		
	}

	
	public Employee(String name, String designation) {
		this.name = name;
		this.designation = designation;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public List<PerformanceReview> getReviews() {
		return reviews;
	}

	public void setReviews(List<PerformanceReview> reviews) {
		this.reviews = reviews;
	}
	
	
	
	
	
}
