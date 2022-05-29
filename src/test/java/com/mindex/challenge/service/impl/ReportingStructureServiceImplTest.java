package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String employeeIdUrl;
    private String employeeUrl;
    private String reportingStructureUrl;

    @Autowired
    private ReportingStructureService reportingStructureService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup(){
        employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
        employeeUrl = "http://localhost:" + port + "/employee";
        reportingStructureUrl = "http://localhost:" + port + "/reportingStructure/{id}";
    }

    @Test
    public void testCreateCalc(){
        //Create an employee with a direct and indirect report
        Employee tempEmp3 = new Employee();
        tempEmp3.setFirstName("Some");
        tempEmp3.setLastName("Other");
        tempEmp3.setDepartment("Engineering");
        tempEmp3.setPosition("Developer3");
        Employee createdEmployee3 = restTemplate.postForEntity(employeeUrl, tempEmp3, Employee.class).getBody();

        Employee tempEmp2 = new Employee();
        tempEmp2.setFirstName("Jane");
        tempEmp2.setLastName("Doe");
        tempEmp2.setDepartment("Engineering");
        tempEmp2.setPosition("Developer2");
        List<Employee> sub2 = new ArrayList<>();
        sub2.add(createdEmployee3);
        tempEmp2.setDirectReports(sub2);
        Employee createdEmployee2 = restTemplate.postForEntity(employeeUrl, tempEmp2, Employee.class).getBody();

        Employee tempEmp = new Employee();
        tempEmp.setFirstName("John");
        tempEmp.setLastName("Doe");
        tempEmp.setDepartment("Engineering");
        tempEmp.setPosition("Developer1");
        List<Employee> sub1 = new ArrayList<>();
        sub1.add(createdEmployee2);
        tempEmp.setDirectReports(sub1);
        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, tempEmp, Employee.class).getBody();

        //Make ReportingStructure
        ReportingStructure manualStruct = new ReportingStructure(createdEmployee, 2);
        ReportingStructure testStruct = restTemplate.getForEntity(reportingStructureUrl, ReportingStructure.class, createdEmployee.getEmployeeId()).getBody();

        //Test ReportingStructure employees are same
        Assert.assertNotNull(testStruct.getEmployee());
        Assert.assertNotNull(testStruct.getNumberOfReports());
        Assert.assertEquals(manualStruct.getNumberOfReports(), 2);
        Assert.assertEquals(testStruct.getNumberOfReports(), 2);
    }
}
