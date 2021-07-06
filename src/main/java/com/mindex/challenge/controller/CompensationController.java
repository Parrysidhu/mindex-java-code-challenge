package com.mindex.challenge.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;


@RestController
public class CompensationController {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
    private CompensationService compensationService;
	
	
	/**
	 * The method is mapped to POST request with the specified URL pattern
	 */
	@PostMapping("/compensation")
    public Compensation create(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for employee with id [{}]", compensation.getEmployee().getEmployeeId());

        return compensationService.create(compensation);
    }
	
	/**
	 * The method is mapped to GET request with the specified URL pattern
	 */
	@GetMapping("/compensation/{id}")
    public Optional<Compensation> read(@PathVariable String id) {
        LOG.debug("Received compensation read request for employee with id [{}]", id);

        return compensationService.read(id);
    }
}
