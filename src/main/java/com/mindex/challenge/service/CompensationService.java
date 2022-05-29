package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

//defines endpoints for Compensation services
public interface CompensationService {
    Compensation create(String id);
    Compensation read(String id);
}
