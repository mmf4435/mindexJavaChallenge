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
     * @param compensation - the new Compensation obj to create
     * @return a new Compensation obj
     */
    @Override
    public Compensation create(Compensation compensation) {
        LOG.debug("Creating Compensation [{}]", compensation);
        Employee employee = employeeRepository.findByEmployeeId(compensation.getEmployeeId());
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId");
        }
        compensation.setEmployee(employee);
        compensationRepository.insert(compensation);
        return compensation;
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
