package com.mindex.challenge.data;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Compensation {

	@DBRef(lazy=true)
	private Employee employee;
	private Double salary;
	private LocalDate effectiveDate;

	public Compensation() {
	}

	public Compensation(Employee employee, Double salary, LocalDate effectiveDate) {
		this.employee = employee;
		this.salary = salary;
		this.effectiveDate = effectiveDate;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Double getSalary() {
		return salary;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public LocalDate getEffectiveDate() {
		return effectiveDate;
	}
	
	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	@Override
	public String toString() {
		return "Compensation {" + 
				"employee=" + employee + 
				", salary=" + salary + 
				", effectiveDate=" + effectiveDate + 
				"}";
	}
}
