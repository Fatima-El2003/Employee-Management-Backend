package com.FatimaElouafi.employeesmanagement.repo;

import com.FatimaElouafi.employeesmanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    void deleteEmployeeById(Long id); //this method is known by Spring he will understand that he wil delete an employee by his id

    Optional<Employee> findEmployeeById(Long id);//spring also will uderstand that it's a method to find employye by id
}
