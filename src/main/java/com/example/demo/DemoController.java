package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/v1/employees")
public class DemoController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int createEmployee(@RequestBody final Employee employee) {
        Employee createdEmployee = employeeRepository.save(employee);
        System.out.println("Git hub is version control tool");
        return createdEmployee.getId();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployee(@PathVariable("id") final int id) {
        return employeeRepository.findById(id).get();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@PathVariable("id") final int id,
                                   @RequestBody final Employee employee) {
        System.out.println("Git hub is version control tool");
        Employee employee1 = employeeRepository.findById(id).get();
        employee1.setName(employee.getName());
        employee1.setEmail(employee.getEmail());
        return employeeRepository.save(employee1);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") final int id) {
        employeeRepository.delete(employeeRepository.findById(id).get());
    }
    public void myMethod(){
        System.out.println("hello");
    }

    public void myMethod1(){
        System.out.println("hi");
    }

    public void myMethod2(){
        System.out.println("hell");
    }

    public void myMethod3(){
        System.out.println("hello1");
    }
}
