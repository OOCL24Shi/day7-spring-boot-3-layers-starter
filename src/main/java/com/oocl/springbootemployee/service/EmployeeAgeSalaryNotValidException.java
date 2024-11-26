package com.oocl.springbootemployee.service;

public class EmployeeAgeSalaryNotValidException extends RuntimeException{
    public EmployeeAgeSalaryNotValidException() {
        super("Employee needs to have a valid age and salary.");
    }
}
