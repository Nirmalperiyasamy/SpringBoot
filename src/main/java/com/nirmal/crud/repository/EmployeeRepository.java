package com.nirmal.crud.repository;

import com.nirmal.crud.dao.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
   boolean existsByUsername(String username);

}
