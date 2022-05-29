package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ReportingStructureController.java
 * @author Matt Favazza
 * @version 5/28/2022
 * Acts as Controller for ReportingStructure service endpoint
 */
@RestController
public class ReportingStructureController {

    //Fields
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);
    @Autowired
    private ReportingStructureService reportingStructureService;

    //Methods
    /**
     * Maps the create method from ReportingStructureService to a GET request.
     * @param id - the employee id to generate a ReportingStructure for
     * @return a new ReportingStructure for this id
     */
    @GetMapping("/reportingStructure/{id}")
    public ReportingStructure create(@PathVariable String id){
        LOG.debug("Received ReportingStructure create request for [{}]", id);
        return reportingStructureService.create(id);
    }
}