package com.FullstackCRUD.EmployeeManagementSystem.exception;

public class employeeNotFoundException extends RuntimeException {
    public employeeNotFoundException(long id){
        super("Employee Not Found with the ID "+id);
    }
}
