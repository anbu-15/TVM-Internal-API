package com.tvm.internal.Controller;

import com.tvm.internal.edit.controller.EmployeesController;
import com.tvm.internal.edit.model.Employees;
import com.tvm.internal.edit.serviceImpl.EmployeesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmployeesControllerTest {

    @Mock
    private EmployeesServiceImpl employeeService;

    @InjectMocks
    private EmployeesController employeesController;

    private Employees employee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employees();
        employee.setEmployeeId(1L);
        employee.setEmployeeName("John Doe");
        employee.setRole("Developer");
        employee.setDetails("Details about John");
        employee.setProfile("john.png");
        employee.setMemberId("M001");
        employee.setMembername("Member One");
        employee.setActiveStatus(true);
        employee.setMemberCount("5");
        employee.setTotalCount("10");
    }

    @Test
    void testGetAllEmployees() {
        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee));

        List<Employees> employeesList = employeesController.getAllEmployees();

        assertNotNull(employeesList);
        assertEquals(1, employeesList.size());
        assertEquals("John Doe", employeesList.get(0).getEmployeeName());
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void testGetEmployeeById() {
        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(employee));

        ResponseEntity<Employees> response = employeesController.getEmployeeById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Doe", response.getBody().getEmployeeName());
        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    void testGetEmployeeById_NotFound() {
        when(employeeService.getEmployeeById(2L)).thenReturn(Optional.empty());

        ResponseEntity<Employees> response = employeesController.getEmployeeById(2L);

        assertEquals(404, response.getStatusCodeValue());
        verify(employeeService, times(1)).getEmployeeById(2L);
    }

    @Test
    void testCreateEmployee() {
        when(employeeService.createEmployee(any(Employees.class))).thenReturn(employee);

        Employees createdEmployee = employeesController.createEmployee(employee);

        assertNotNull(createdEmployee);
        assertEquals("John Doe", createdEmployee.getEmployeeName());
        verify(employeeService, times(1)).createEmployee(any(Employees.class));
    }

    @Test
    void testUpdateEmployee() {
        when(employeeService.updateEmployee(eq(1L), any(Employees.class))).thenReturn(employee);

        ResponseEntity<Employees> response = employeesController.updateEmployee(1L, employee);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Doe", response.getBody().getEmployeeName());
        verify(employeeService, times(1)).updateEmployee(eq(1L), any(Employees.class));
    }

    @Test
    void testDeleteEmployee() {
        doNothing().when(employeeService).deleteEmployee(1L);

        ResponseEntity<Void> response = employeesController.deleteEmployee(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(employeeService, times(1)).deleteEmployee(1L);
    }
}

