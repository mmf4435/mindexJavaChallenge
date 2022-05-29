package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * CompensationRepository.java
 * @author Matt Favazza
 * @version 5/29/2022
 * Repository for Compensation objects
 */
@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    //Defines a lookup method for the repository
    Compensation findByEmployeeId(String employeeId);
}
