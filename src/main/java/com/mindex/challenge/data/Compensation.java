package com.mindex.challenge.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * Compensation.java
 * @author Matt Favazza
 * @version 5/29/2022
 * Type that connects an employee to their compensation
 */
public class Compensation {

    //Fields
    private String employeeId;
    private Employee employee;
    private BigDecimal salary;
    private LocalDate effectiveDate;

    //Constructor
    public Compensation(BigDecimal salary, LocalDate effectiveDate){
        this.salary = salary;
        salary.setScale(2, RoundingMode.CEILING);
        this.effectiveDate = effectiveDate;
    }

    //Methods: getters and setters for fields
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
