package com.ot.employeemanagement.employee.repository;

import com.ot.employeemanagement.employee.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    boolean existsByEmail(final String employeeEmail);
}
