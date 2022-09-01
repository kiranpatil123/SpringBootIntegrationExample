/*
package com.ot.employeemanagement.exception;

import com.ot.employeemanagement.employee.exception.EmployeeManagementException;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@NoArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(value = EmployeeManagementException.class)
    public ErrorResponse employeeManagementException(final EmployeeManagementException e) {
        return new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}
*/
