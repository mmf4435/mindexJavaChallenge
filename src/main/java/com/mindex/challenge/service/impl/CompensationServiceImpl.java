package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * CompensationServiceImpl.java
 * @author Matt Favazza
 * @version 5/29/2022
 * Implements the CompensationService Interface with two REST endpoints
 */
@Service
public class CompensationServiceImpl implements CompensationService {

    //Fields
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationRepository compensationRepository;

    //Methods

    /**
     * Creates a new Compensation obj based on an employee id
     * @param id - employee id for an employee to have a compensation specified for
     * @return a new Compensation obj
     */
    @Override
    public Compensation create(String id) {
        LOG.debug("Creating Compensation [{}]", id);
        Employee employee = employeeRepository.findByEmployeeId(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        Compensation comp = new Compensation(new BigDecimal("0.00"), LocalDate.of(1000, 1, 1));
        comp.setEmployeeId(id);
        comp.setEmployee(employee);
        compensationRepository.insert(comp);
        return comp;
    }

    /**
     * Finds an already existing compensation obj in the persistence layer
     * @param id - employee id for an employee that has already had their compensation specified for
     * @return a compensation obj based on the one found in the persistence layer
     */
    @Override
    public Compensation read(String id) {
        LOG.debug("Reading Compensation [{}]", id);
        Compensation comp = compensationRepository.findByEmployeeId(id);
        if (comp == null){
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        return comp;
    }
}
