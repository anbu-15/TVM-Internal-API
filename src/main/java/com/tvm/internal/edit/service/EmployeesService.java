package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.Employee;
import com.tvm.internal.edit.model.Employees;

import java.util.List;
import java.util.Optional;

public interface EmployeesService{
    List<Employees> getAllEmployees();
    Optional<Employees> getEmployeeById(Long id);
    Employees createEmployee(Employees employee);
    Employees updateEmployee(Long id, Employees employeeDetails);
    void deleteEmployee(Long id);
}
