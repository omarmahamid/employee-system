package com.omar.employee.exceptions;

public class EmployeeDataNotFoundException extends RuntimeException {
    public EmployeeDataNotFoundException(String message) {
        super(message);
    }
}
