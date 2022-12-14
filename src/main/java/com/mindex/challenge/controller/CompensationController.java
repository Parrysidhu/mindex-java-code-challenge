package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class CompensationController {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
    private CompensationService compensationService;

	/**
	 * The method is mapped to POST request with the specified URL pattern
	 */
	@PostMapping("/employee/compensation")
    public ResponseEntity<Compensation> create(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for employee with id [{}]", compensation.getEmployee().getEmployeeId());

		return new ResponseEntity<>(compensationService.create(compensation), HttpStatus.OK);
    }
	
	/**
	 * The method is mapped to GET request with the specified URL pattern
	 */
	@GetMapping("/employee/compensation/{id}")
    public ResponseEntity<Compensation> read(@PathVariable String id) throws Exception {
        LOG.debug("Received compensation read request for employee with id [{}]", id);
		Compensation compensation = compensationService.read(id);

		if (compensation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(compensation, HttpStatus.OK);
    }
}
