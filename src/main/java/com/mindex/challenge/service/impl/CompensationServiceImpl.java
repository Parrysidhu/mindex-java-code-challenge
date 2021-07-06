package com.mindex.challenge.service.impl;
import java.util.Optional;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Compensation}
 */
@Service
public class CompensationServiceImpl implements CompensationService{

	private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);
	
	@Autowired
    private CompensationRepository compensationRepository;
	
	@Autowired
    private EmployeeRepository employeeRepository;
	
	
	/**
	 * Method to create Compensation as per the GET Request
	 * @param compensation object received from client 
	 */
	@Override
	public Compensation create(Compensation compensation) {
		LOG.debug("Creating compensation for employee with id  [{}]", compensation.getEmployee().getEmployeeId());
		
		
		compensationRepository.save(compensation);
		
		return compensation;
	}

	/**
	 * Method to read and return compensation object
	 * @param Id of employee received from client
	 * @exception RuntimeException on getting a null Employee object for employee id
	 * @exception RuntimeException on getting a null Compensation object for the employee
	 */
	@Override
	public Optional<Compensation> read(String id) {
		LOG.debug("Get compensation for employee with id [{}]", id);
		
		Employee employee = employeeRepository.findByEmployeeId(id);
		
		if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        } 
		
		Optional <Compensation> compensation = compensationRepository.findById(employee);
		
		if (compensation.isEmpty()) {
            throw new RuntimeException("Compensation not found for employeeId: " + id);
        }
		
		return compensation;
	}

}
