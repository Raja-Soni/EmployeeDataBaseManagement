package com.FullstackCRUD.EmployeeManagementSystem.controller;

import com.FullstackCRUD.EmployeeManagementSystem.exception.employeeNotFoundException;
import com.FullstackCRUD.EmployeeManagementSystem.model.Employee;
import com.FullstackCRUD.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class EmployeeController {
        @Autowired
        EmployeeRepository employeeRepository;

        @GetMapping("/allTheEmployees")
        List<Employee> allTheEmployees()
        {
            return employeeRepository.findAll();
        }

        @GetMapping("/allTheEmployees/{id}")
        Employee employeeById(@PathVariable long id){
                return employeeRepository.findById(id).orElseThrow(()->new employeeNotFoundException(id));
        }

        @PostMapping("/addnewEmployee")
        Employee newEmploye(@RequestBody Employee newEmp){
                return employeeRepository.save(newEmp);
        }

        @DeleteMapping("/allTheEmployees/{id}")
        String deleteSelectedEmployee(@PathVariable long id){
                if(!employeeRepository.existsById(id)){
                        throw new employeeNotFoundException(id);
                }
               employeeRepository.deleteById(id);
               return "Employee is deleted with the ID "+id;
        }

        @PutMapping("/EditEmployeeDetails/{id}")
        Employee updateEmployeeDetails(@RequestBody Employee newDetailsOfEmployee,@PathVariable long id){
                return employeeRepository.findById(id)
                        .map(emp->{
                        emp.setFirstName(newDetailsOfEmployee.getFirstName());
                        emp.setLastName(newDetailsOfEmployee.getLastName());
                        emp.setEmailId(newDetailsOfEmployee.getEmailId());
                        return  employeeRepository.save(emp);
                        }

                ).orElseThrow(()->new employeeNotFoundException(id));
        }


}
