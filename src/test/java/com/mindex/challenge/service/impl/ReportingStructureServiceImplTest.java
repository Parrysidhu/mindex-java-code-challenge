package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String reportingStructureIdUrl;
    private Employee createdEmployee;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Before
    public void Setup() {
        reportingStructureIdUrl = "http://localhost:" + port + "/employee/reportingStructure/{id}";

        createdEmployee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    }

    @Test
    public void testRead() {

        ReportingStructure testReportingStructure = new ReportingStructure();
        testReportingStructure.setEmployee(createdEmployee);
        testReportingStructure.setNumberOfReports(4);

        //Read Checks
        ReportingStructure readReportingStructure = restTemplate.getForEntity(reportingStructureIdUrl, ReportingStructure.class, testReportingStructure.getEmployee().getEmployeeId()).getBody();

        assertEquals(testReportingStructure.getEmployee().getEmployeeId(), readReportingStructure.getEmployee().getEmployeeId());
        assertCompensationEquivalence(testReportingStructure, readReportingStructure);
    }

    private static void assertCompensationEquivalence(ReportingStructure expected, ReportingStructure actual) {
        assertEquals(expected.getEmployee(), expected.getEmployee());
        assertEquals(expected.getNumberOfReports(), actual.getNumberOfReports());
    }
}