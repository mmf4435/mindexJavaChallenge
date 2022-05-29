package com.mindex.challenge.data;

/**
 * ReportingStructure.java
 * @author Matt Favazza
 * @version 5/28/2022
 * Type that connects an employee to the total number of reports they have
 */
public class ReportingStructure {

    //Fields
    private Employee employee;
    private int numberOfReports;

    //Constructor
    public ReportingStructure(Employee employee, int numberOfReports){
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }

    //Methods: getters and setters for fields
    public Employee getEmployee(){
        return employee;
    }

    public void setEmployee(Employee emp){
        employee = emp;
    }

    public int getNumberOfReports(){
        return numberOfReports;
    }

    public void setNumberOfReports(int num) {
        this.numberOfReports = num;
    }
}
