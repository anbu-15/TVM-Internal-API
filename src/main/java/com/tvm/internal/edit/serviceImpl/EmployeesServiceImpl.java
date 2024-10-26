package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Employees;
import com.tvm.internal.edit.repo.EmployeesRepository;
import com.tvm.internal.edit.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesServiceImpl implements EmployeesService {

    @Autowired
    private EmployeesRepository employeeRepository;

    @Override
    public List<Employees> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employees> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employees createEmployee(Employees employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employees updateEmployee(Long id, Employees employeeDetails) {
        Optional<Employees> existingEmployeeOptional = employeeRepository.findById(id);

        if (existingEmployeeOptional.isPresent()) {
            Employees existingEmployee = existingEmployeeOptional.get();

            // Update fields
            existingEmployee.setEmployeeName(employeeDetails.getEmployeeName() != null ? employeeDetails.getEmployeeName() : existingEmployee.getEmployeeName());
            existingEmployee.setDetails(employeeDetails.getDetails() != null ? employeeDetails.getDetails() : existingEmployee.getDetails());
            existingEmployee.setRole(employeeDetails.getRole() != null ? employeeDetails.getRole() : existingEmployee.getRole());
            existingEmployee.setProfile(employeeDetails.getProfile() != null ? employeeDetails.getProfile() : existingEmployee.getProfile());
            existingEmployee.setMemberId(employeeDetails.getMemberId() != null ? employeeDetails.getMemberId() : existingEmployee.getMemberId());
            existingEmployee.setMembername(employeeDetails.getMembername() != null ? employeeDetails.getMembername() : existingEmployee.getMembername());
            existingEmployee.setActiveStatus(employeeDetails.isActiveStatus());
            existingEmployee.setMemberCount(employeeDetails.getMemberCount() != null ? employeeDetails.getMemberCount() : existingEmployee.getMemberCount());
            existingEmployee.setTotalCount(employeeDetails.getTotalCount() != null ? employeeDetails.getTotalCount() : existingEmployee.getTotalCount());

            // Save the updated entity
            return employeeRepository.save(existingEmployee);
        } else {
            throw new RuntimeException("Employee not found with id " + id);
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}