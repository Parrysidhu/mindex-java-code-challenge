package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link ReportingStructure}
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeService employeeService;

	/**
	 * Method to generate Reporting Structure after calculating the number of reports for the employee with ID : id
	 * @param id of employee
	 * @exception RuntimeException on getting a null Employee object for employee id
	 */
	@Override
	public ReportingStructure getReportingStructure(String id) {
		LOG.debug("Creating reporting structure for employee with id [{}]", id);
		
		Employee employee = employeeService.read(id);
		
		if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

		int numberOfReports = totalReports(employee);
		return new ReportingStructure(employee, numberOfReports);
	}
	
	/**
	 * Helper method to calculate total number of reports for an employee
	 * @param employee object of Employee class
	 * @return the total number of reports 
	 */
	public int totalReports(Employee employee) {
		if(employee.getDirectReports() == null) return 0;
		
		return employee.getDirectReports().size() +
                employee.getDirectReports().stream()
                        .map(e -> employeeService.read(e.getEmployeeId()))
                        .filter(e -> e.getDirectReports() != null)
                        .map(e -> totalReports(e))
                        .reduce(0, (sum, e) -> sum + e);	}
}