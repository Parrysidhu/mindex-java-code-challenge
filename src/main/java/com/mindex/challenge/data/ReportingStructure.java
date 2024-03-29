package com.mindex.challenge.data;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ReportingStructure {
	
	private Employee employee;
	private Integer numberOfReports;
	
	public ReportingStructure()	{
	}
	
	public ReportingStructure(Employee employee,Integer numberOfReports )	{
		this.employee = employee;
		this.numberOfReports = numberOfReports;
	}
	

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getNumberOfReports() {
		return numberOfReports;
	}

	public void setNumberOfReports(Integer numberOfReports) {
		this.numberOfReports = numberOfReports;
	}

	@Override
	public String toString() {
		return "ReportingStructure {" +
				"employee=" + employee +
				", numberOfReports=" + numberOfReports +
				"}";
	}
	
}
