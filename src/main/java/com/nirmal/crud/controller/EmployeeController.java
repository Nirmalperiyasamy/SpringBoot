package com.nirmal.crud.controller;

import com.nirmal.crud.dao.Employee;
import com.nirmal.crud.dto.EmployeeModel;
import com.nirmal.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeModel employee) {
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel = employeeService.addEmployee(employee);
        if (employeeModel == null) return ResponseEntity.ok().body("username already exist");

        return ResponseEntity.ok(employeeModel);
    }

    @GetMapping("/")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable int id) {
        boolean delete = false;
        delete = employeeService.deleteEmployee(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", delete);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable int id) {
        EmployeeModel employee = null;
        employee = employeeService.getEmployee(id);
        if (employee == null) return ResponseEntity.ok().body("user not found");

        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeModel> updateEmployee(@PathVariable int id, @RequestBody EmployeeModel employee) {
        employee = employeeService.updateEmployee(id, employee);
        System.out.println(employee);

        return ResponseEntity.ok(employee);
    }


}
