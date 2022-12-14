package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingStructureController {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
    private ReportingStructureService reportingStructureService;

	/**
	 * The method is mapped to GET request with the specified URL pattern
	 */
	@GetMapping("/employee/reportingStructure/{id}")
    public ResponseEntity<ReportingStructure> read(@PathVariable String id) {
        LOG.debug("Received reporting structure create request for id [{}]", id);

		ReportingStructure reportingStructure = reportingStructureService.getReportingStructure(id);
		if (reportingStructure != null) {
			return new ResponseEntity<>(reportingStructure, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
