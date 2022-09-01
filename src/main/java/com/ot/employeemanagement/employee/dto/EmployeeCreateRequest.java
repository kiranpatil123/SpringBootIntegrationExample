package com.ot.employeemanagement.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EmployeeCreateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
}
