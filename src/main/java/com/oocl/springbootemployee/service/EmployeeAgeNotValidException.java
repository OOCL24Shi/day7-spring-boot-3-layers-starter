package com.oocl.springbootemployee.service;

public class EmployeeAgeNotValidException extends RuntimeException{
    public EmployeeAgeNotValidException() {
        super("Employee Age Not Valid: ");
    }
}
