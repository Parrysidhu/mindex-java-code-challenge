package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;

import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Compensation}
 */
@Service
public class CompensationServiceImpl implements CompensationService {

	private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

	@Autowired
    private CompensationRepository compensationRepository;

	@Autowired
	private EmployeeService employeeService;

	/**
	 * Method to create Compensation as per the GET Request
	 * @param compensation object received from client
	 */
	@Override
	public Compensation create(Compensation compensation) {
		LOG.debug("Creating compensation for employee with id  [{}]", compensation.getEmployee().getEmployeeId());

		Employee employee = employeeService.read(compensation.getEmployee().getEmployeeId());
		compensation.setEmployee(employee);
		compensationRepository.insert(compensation);

		return compensation;
	}

	/**
	 * Method to read and return compensation object
	 * @param id of employee received from client
	 * @exception Exception if there is an error finding the employee with given id
	 */
	@Override
	public Compensation read(String id) throws Exception {
		LOG.debug("Get compensation for employee with id [{}]", id);

		Compensation compensation;
		try {
			compensation = compensationRepository.findByEmployee_EmployeeId(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return compensation;
	}
}