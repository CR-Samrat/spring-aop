package com.example.spring_aop.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.example.spring_aop.model.Employee;

import jakarta.annotation.PostConstruct;

@Service
public class EmployeeService {

    List<Employee> employees = null;
    
    @PostConstruct
    public void loadEmployees(){
        employees = IntStream.rangeClosed(1, 100)
                                .mapToObj(i -> Employee.builder()
                                                        .id(i)
                                                        .name("Employee "+i)
                                                        .salary(new Random().nextInt(100000))
                                                        .increment(new Random().nextInt(20))
                                                        .build())
                                .collect(Collectors.toList());
    }

    public List<Employee> getAllEmployees(){
        return employees;
    }

    public Employee getEmployeeById(int id){
        System.out.println("Get Employee by ID method is called");

        return employees.stream()
                .filter(emp -> id == emp.getId())
                .findAny()
                .orElseThrow(()-> new RuntimeException("Invalid id "+id));
    }
}
