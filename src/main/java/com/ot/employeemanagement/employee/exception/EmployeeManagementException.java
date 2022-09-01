package com.ot.employeemanagement.employee.exception;

public class EmployeeManagementException extends RuntimeException {
    private final String message;

    public EmployeeManagementException(final String message) {
        super(message);
        this.message = message;
    }
}
