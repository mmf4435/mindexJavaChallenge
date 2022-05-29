package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String employeeIdUrl;
    private String employeeUrl;
    private String compensationCreateUrl;
    private String compensationReadUrl;

    @Autowired
    private CompensationService CompensationService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup(){
        employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
        employeeUrl = "http://localhost:" + port + "/employee";
        compensationCreateUrl = "http://localhost:" + port + "/compensation/create/{id}";
        compensationReadUrl = "http://localhost:" + port + "/compensation/read/{id}";
    }

    @Test
    public void testCreateRead(){
        //Create employee component
        Employee tempEmp = new Employee();
        tempEmp.setFirstName("John");
        tempEmp.setLastName("Doe");
        tempEmp.setDepartment("Engineering");
        tempEmp.setPosition("Developer1");
        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, tempEmp, Employee.class).getBody();

        //create temp component to test against
        Compensation comp = new Compensation(new BigDecimal("0.00"), LocalDate.of(1000, 1, 1));
        comp.setEmployeeId(createdEmployee.getEmployeeId());
        comp.setEmployee(createdEmployee);

        //simulate creation
        Compensation createdCompensation = restTemplate.postForEntity(compensationCreateUrl, comp, Compensation.class, comp.getEmployeeId()).getBody();

        //Test created obj
        Assert.assertNotNull(createdCompensation);
        assertCompensationEquivalence(comp, createdCompensation);

        //Test read obj
        Compensation readCompensation = restTemplate.getForEntity(compensationReadUrl, Compensation.class, comp.getEmployeeId()).getBody();
        Assert.assertNotNull(readCompensation);
        assertCompensationEquivalence(readCompensation, comp);
    }

    private static void assertCompensationEquivalence(Compensation expected, Compensation actual){
        Assert.assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
        Assert.assertEquals(expected.getSalary(), actual.getSalary());
        Assert.assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
    }
}
