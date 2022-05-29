package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ReportingStructureImpl.java
 * @author Matt Favazza
 * @version 5/28/2022
 * Implements the ReportingStructureService interface with one REST endpoint
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    //Fields
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);
    @Autowired
    private EmployeeService employeeService;

    //Methods
    /**
     * Creates new ReportingStructure object from a valid employee ID.
     * Throws exception if employee doesn't exist.
     * @param id - an employee ID.
     * @return a new ReportingStructure object complete with employee and number of reports
     */
    @Override
    public ReportingStructure create(String id) {
        LOG.debug("Creating ReportingStructure [{}]", id);
        Employee employee = employeeService.read(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        int reports = calcTotalReports(employee);
        return new ReportingStructure(employee, reports);
    }

    /**
     * Calculates the number of reports that an employee has.
     * @param emp - the employee to calculate the number of reports for
     * @return an int representing the number of reports that an employee has
     */
    private int calcTotalReports(Employee emp){
        if(emp.getDirectReports() == null){
            return 0;
        }
        else{
            int reports = 0;
            for(Employee subEmp: emp.getDirectReports()){
                Employee tempSubEmp = employeeService.read(subEmp.getEmployeeId());
                reports += calcTotalReports(tempSubEmp);
            }
            return emp.getDirectReports().size() + reports;
        }
    }
}
