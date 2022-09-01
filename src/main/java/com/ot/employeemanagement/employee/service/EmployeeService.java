package com.ot.employeemanagement.employee.service;

import com.ot.employeemanagement.employee.domain.Employee;
import com.ot.employeemanagement.employee.dto.EmployeeCreateRequest;
import com.ot.employeemanagement.employee.exception.EmployeeManagementException;
import com.ot.employeemanagement.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Integer createEmployee(final EmployeeCreateRequest employeeCreateRequest) {
        checkForExistingEmployee(employeeCreateRequest.getEmail());
        Employee employee = convertToEmployee(employeeCreateRequest);
        Employee createdEmployee = employeeRepository.save(employee);
        return createdEmployee.getId();
    }

    public Employee getEmployee(final Integer employeeId) {
        return findByEmployeeId(employeeId);
    }

    public Employee updateEmployee(final Integer employeeId,
                                   final EmployeeCreateRequest employeeUpdateRequest) {
        Employee employee = findByEmployeeId(employeeId);
        employee.setEmail(employeeUpdateRequest.getEmail());
        employee.setName(employeeUpdateRequest.getName());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(final Integer employeeId) {
        Employee employee = findByEmployeeId(employeeId);
        employeeRepository.delete(employee);
    }

    private Employee findByEmployeeId(final Integer employeeId) {
        if (Objects.isNull(employeeId)) {
            throw new EmployeeManagementException("Employee id should not be null");
        }
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeManagementException(String.format("Employee not present with given employee id %d", employeeId)));
    }

    private void checkForExistingEmployee(final String employeeEmail) {
        if (employeeRepository.existsByEmail(employeeEmail)) {
            throw new EmployeeManagementException(String.format("Employee email %s is already present with different employee", employeeEmail));
        }
    }

    private Employee convertToEmployee(final EmployeeCreateRequest employeeCreateRequest) {
        return Employee.builder()
                .name(employeeCreateRequest.getName())
                .email(employeeCreateRequest.getEmail())
                .build();
    }
}
