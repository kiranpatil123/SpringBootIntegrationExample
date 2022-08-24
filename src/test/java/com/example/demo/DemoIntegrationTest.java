package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class DemoIntegrationTest {
    private static final String EMPLOYEE_PATH = "/api/v1/employees";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void init() {
        employeeRepository.deleteAll();
    }

    @Test
    public void addEmployeeTest() throws Exception {
        Employee employee = new Employee();
        employee.setName("kiran");
        employee.setEmail("kiran@gmail.com");
        mockMvc.perform(post(EMPLOYEE_PATH).contentType(MediaType.APPLICATION_JSON)
                        .content(convertToJsonString(employee))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        employeeRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void getEmployeeTest() throws Exception {
        Employee createdEmployee = createEmployee();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(EMPLOYEE_PATH + "/{id}", createdEmployee.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Employee employee1 = convertToObject(mvcResult.getResponse().getContentAsString(), Employee.class);
        System.out.println(employee1);
    }

    @Test
    public void updateEmployeeTest() throws Exception {
        Employee createdEmployee = createEmployee();
        Employee updateRequest = new Employee(createdEmployee.getId(), "Patil", "patil@gmail.com");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(EMPLOYEE_PATH + "/{id}", createdEmployee.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertToJsonString(updateRequest))).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        Employee updatedEmployee = convertToObject(mvcResult.getResponse().getContentAsString(), Employee.class);
        Assertions.assertEquals("Patil", updatedEmployee.getName());
        Assertions.assertEquals("patil@gmail.com", updatedEmployee.getEmail());
    }

    @Test
    public void deleteEmployeeTest() throws Exception {
        Employee createdEmployee = createEmployee();
        Employee employee = employeeRepository.findById(createdEmployee.getId()).get();
        Assertions.assertNotNull(employee);
        mockMvc.perform(MockMvcRequestBuilders.delete(EMPLOYEE_PATH + "/{id}", createdEmployee.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    private String convertToJsonString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    private <T> T convertToObject(String jsonString, Class<T> t) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, t);
    }

    private Employee createEmployee() {
        Employee employee = new Employee();
        employee.setName("kiran");
        employee.setEmail("kiran@gmail.com");
        return employeeRepository.save(employee);
    }
}
