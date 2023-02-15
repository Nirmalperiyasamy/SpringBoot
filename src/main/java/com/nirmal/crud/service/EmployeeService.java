package com.nirmal.crud.service;

import com.nirmal.crud.dao.Employee;
import com.nirmal.crud.dto.EmployeeModel;
import com.nirmal.crud.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public boolean isUsernameAlreadyTaken(String username) {
        return employeeRepository.existsByUsername(username);
    }

    public EmployeeModel addEmployee(EmployeeModel emp) {
        if(isUsernameAlreadyTaken(emp.getUsername())){
            System.out.println(emp);
            emp=null;
            System.out.println(emp);
            return emp;
        }
        else {
            Employee employee = new Employee();
            BeanUtils.copyProperties(emp, employee);
            employee = employeeRepository.save(employee);
            BeanUtils.copyProperties(employee, emp);

            return emp;
        }
    }

    public List<Employee> getAll() {
        List<Employee> emp = employeeRepository.findAll();

        return emp;
    }

    public boolean deleteEmployee(int id) {
        Employee employee = employeeRepository.findById(id).get();
        employeeRepository.delete(employee);

        return true;
    }

    public EmployeeModel getEmployee(int id) {
        try {
            Employee employee = employeeRepository.findById(id).get();
            EmployeeModel employeeModel = new EmployeeModel();
            BeanUtils.copyProperties(employee, employeeModel);

            return employeeModel;
        } catch (NoSuchElementException e) {

            return null;
        }
    }

    public EmployeeModel updateEmployee(int id, EmployeeModel employee) {
        Employee employee1 = employeeRepository.findById(id).get();
        employee1.setAge(employee.getAge());
        employee1.setUsername(employee.getUsername());
        employeeRepository.save(employee1);
        BeanUtils.copyProperties(employee1, employee);

        return employee;
    }

}
