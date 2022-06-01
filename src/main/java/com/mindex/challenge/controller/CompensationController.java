package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * CompensationController.java
 * @author Matt Favazza
 * @version 5/29/2022
 * Acts as Controller for Compensation service endpoint
 */
@RestController
public class CompensationController {

    //Fields
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);
    @Autowired
    private CompensationService compensationService;

    //Methods
    /**
     * post mapping for compensation model
     * @param compensation - the new compensation obj being created
     * @return - a compensation obj
     */
    @PostMapping("/compensation/create/{id}")
    public Compensation create(@RequestBody Compensation compensation){
        LOG.debug("Received compensation create request for [{}]", compensation);
        return compensationService.create(compensation);
    }

    /**
     * get mapping for compensation model
     * @param id - employee id for an employee that has already received a compensation specification
     * @return - the stored compensation obj
     */
    @GetMapping("/compensation/read/{id}")
    public Compensation read(@PathVariable String id){
        LOG.debug("Received compensation read request for [{}]", id);
        return compensationService.read(id);
    }
}
