package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * Service Implementation for managing {@link ReportingStructure}
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService{

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
    private EmployeeRepository employeeRepository;
	
	/**
	 * Method to generate Reporting Structure after calculating the number of reports for the employee with ID : id
	 * @param id of employee
	 * @exception RuntimeException on getting a null employee id
	 */
	@Override
	public ReportingStructure returnReportingStructure(String id) {
		LOG.debug("Creating reporting structure with id [{}]", id);
		
		Employee employee = employeeRepository.findByEmployeeId(id);
		
		if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
		
		
		int numberOfReports = totalReports(employee);
		ReportingStructure reportingStructure = new ReportingStructure(employee, numberOfReports);
		
		return reportingStructure;
	}
	
	
	/**
	 * Helper method to calculate total number of reports for an employee
	 * @param object of Employee class
	 * @return the total number of reports 
	 */
	public int totalReports(Employee employee) {
		if(employee.getDirectReports() == null) return 0;
		
		return employee.getDirectReports().size() +
                employee.getDirectReports().stream()
                        .map(e -> employeeRepository.findByEmployeeId(e.getEmployeeId()))
                        .filter(e -> e.getDirectReports() != null)
                        .map(e -> totalReports(e))
                        .reduce(0, (sum, e) -> sum + e);	}
}
