package com.ot.employeemanagement.employee.controller;

import com.ot.employeemanagement.employee.domain.Employee;
import com.ot.employeemanagement.employee.dto.EmployeeCreateRequest;
import com.ot.employeemanagement.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {


    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createEmployee(@RequestBody @Valid final EmployeeCreateRequest employeeCreateRequest) {
        return employeeService.createEmployee(employeeCreateRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployee(@PathVariable("id") final int employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@PathVariable("id") final int employeeId,
                                   @RequestBody final EmployeeCreateRequest employeeCreateRequest) {
        return employeeService.updateEmployee(employeeId, employeeCreateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") final int employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}
