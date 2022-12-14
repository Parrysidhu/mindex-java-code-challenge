package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String compensationUrl;
    private String compensationIdUrl;
    private Employee createdEmployee;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/employee/compensation";
        compensationIdUrl = "http://localhost:" + port + "/employee/compensation/{id}";
        String employeeUrl = "http://localhost:" + port + "/employee";

        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        createdEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();
    }

    @Test
    public void testCreateRead() {

        Compensation testCompensation = new Compensation(createdEmployee, 1000.00, LocalDate.parse("2023-01-01"));

        //Create checks
        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class).getBody();

        assertNotNull(createdCompensation.getEmployee().getEmployeeId());
        assertCompensationEquivalence(testCompensation, createdCompensation);


        //Read checks
        Compensation readCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, createdCompensation.getEmployee().getEmployeeId()).getBody();

        assertEquals(createdCompensation.getEmployee().getEmployeeId(), readCompensation.getEmployee().getEmployeeId());
        assertCompensationEquivalence(createdCompensation, readCompensation);
    }

    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getEmployee(), expected.getEmployee());
        assertEquals(expected.getSalary(), actual.getSalary());
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
    }
}