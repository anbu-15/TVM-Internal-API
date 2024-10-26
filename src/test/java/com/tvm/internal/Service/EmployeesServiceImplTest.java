package com.tvm.internal.Service;

import com.tvm.internal.edit.model.Employees;
import com.tvm.internal.edit.repo.EmployeesRepository;
import com.tvm.internal.edit.serviceImpl.EmployeesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class EmployeesServiceImplTest {

    @Mock
    private EmployeesRepository employeeRepository;

    @InjectMocks
    private EmployeesServiceImpl employeesService;

    private Employees employee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employees();
        employee.setEmployeeId(1L);
        employee.setEmployeeName("John Doe");
        employee.setDetails("Details about John");
        employee.setRole("Developer");
        employee.setProfile("Profile Info");
        employee.setMemberId("M123");
        employee.setMembername("John Member");
        employee.setActiveStatus(true);
        employee.setMemberCount("5");
        employee.setTotalCount("10");
    }

    @Test
    void testGetAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));

        List<Employees> employeesList = employeesService.getAllEmployees();

        assertEquals(1, employeesList.size());
        assertEquals(employee.getEmployeeName(), employeesList.get(0).getEmployeeName());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testGetEmployeeById() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

        Optional<Employees> foundEmployee = employeesService.getEmployeeById(1L);

        assertTrue(foundEmployee.isPresent());
        assertEquals(employee.getEmployeeName(), foundEmployee.get().getEmployeeName());
        verify(employeeRepository, times(1)).findById(anyLong());
    }

    @Test
    void testCreateEmployee() {
        when(employeeRepository.save(any(Employees.class))).thenReturn(employee);

        Employees createdEmployee = employeesService.createEmployee(employee);

        assertNotNull(createdEmployee);
        assertEquals(employee.getEmployeeName(), createdEmployee.getEmployeeName());
        verify(employeeRepository, times(1)).save(any(Employees.class));
    }

    @Test
    void testUpdateEmployee_Success() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employees.class))).thenReturn(employee);

        Employees updatedEmployee = new Employees();
        updatedEmployee.setEmployeeName("Jane Doe");

        Employees result = employeesService.updateEmployee(1L, updatedEmployee);

        assertEquals("Jane Doe", result.getEmployeeName());
        verify(employeeRepository, times(1)).findById(anyLong());
        verify(employeeRepository, times(1)).save(any(Employees.class));
    }

    @Test
    void testUpdateEmployee_NotFound() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            employeesService.updateEmployee(1L, employee);
        });

        assertEquals("Employee not found with id 1", exception.getMessage());
        verify(employeeRepository, times(1)).findById(anyLong());
        verify(employeeRepository, never()).save(any(Employees.class));
    }

    @Test
    void testDeleteEmployee() {
        doNothing().when(employeeRepository).deleteById(anyLong());

        employeesService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).deleteById(anyLong());
    }
}
