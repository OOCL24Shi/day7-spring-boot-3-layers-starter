package com.oocl.springbootemployee.service;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.EmployeeRepository;
import com.oocl.springbootemployee.repository.IEmployeeRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class EmployeeServiceTest {
    @Test
    void should_return_the_given_employees_when_getAllEmployees() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        when(mockedEmployeeRepository.getAll()).thenReturn(List.of(new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0)));
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        List<Employee> allEmployees = employeeService.getAllEmployees();

        //then
        assertEquals(1, allEmployees.size());
        assertEquals("Lucy", allEmployees.get(0).getName());
    }

    @Test
    void should_return_the_created_employee_when_create_given_a_employee() {
        //given
        IEmployeeRepository mockedEmployeeRepository = mock(IEmployeeRepository.class);
        Employee lucy = new Employee(1, "Lucy", 18, Gender.FEMALE, 8000.0);
        when(mockedEmployeeRepository.addEmployee(any())).thenReturn(lucy);
        EmployeeService employeeService = new EmployeeService(mockedEmployeeRepository);

        //when
        Employee createdEmployee = employeeService.creat(lucy);

        //then
        assertEquals("Lucy", createdEmployee.getName());
    }

    //employees those who are not 18-65 years old cannot be created
    //employees over 30 years of age (inclusive) with a salary below 20000 cannot be created
    @Test
    void should_return_error_when_create_given_age_less_than_18() {
        //Given
        EmployeeRepository mockEmployeeRepository = mock(EmployeeRepository.class);
        Employee lucy = new Employee(1, "Lucy", 6, Gender.FEMALE, 8000.0);
        EmployeeService employeeService = new EmployeeService(mockEmployeeRepository);
        //When

        //Then
        assertThrows(EmployeeAgeNotValidException.class, () -> employeeService.creat(lucy));
        verify(mockEmployeeRepository, never()).addEmployee(any());
    }

    @Test
    void should_return_error_when_create_given_age_higher_than_65() {
        //Given
        EmployeeRepository mockEmployeeRepository = mock(EmployeeRepository.class);
        Employee lucy = new Employee(1, "Lucy", 68, Gender.FEMALE, 8000.0);
        EmployeeService employeeService = new EmployeeService(mockEmployeeRepository);
        //When

        //Then
        assertThrows(EmployeeAgeNotValidException.class, () -> employeeService.creat(lucy));
        verify(mockEmployeeRepository, never()).addEmployee(any());
    }
}
