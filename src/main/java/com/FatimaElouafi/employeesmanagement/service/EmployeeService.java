package com.FatimaElouafi.employeesmanagement.service;

import com.FatimaElouafi.employeesmanagement.exception.NotFoundEmployyeExcetion;
import com.FatimaElouafi.employeesmanagement.model.Employee;
import com.FatimaElouafi.employeesmanagement.repo.EmployeeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee, MultipartFile file) throws IOException {
        // Generate employee code
        employee.setEmployeeCode(UUID.randomUUID().toString());

        // Save file
        if (file != null && !file.isEmpty()) {
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get("uploads");

            // Create directory if not exists
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Save the file
            Files.write(uploadPath.resolve(filename), file.getBytes());

            // Set the file path to employee's imageUrl
            employee.setImageUrl(filename);
        }

        // Save employee data
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee, MultipartFile file) throws IOException {
        // Save file
        if (file != null && !file.isEmpty()) {
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get("uploads");

            // Create directory if not exists
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Save the file
            Files.write(uploadPath.resolve(filename), file.getBytes());

            // Set the file path to employee's imageUrl
            employee.setImageUrl(filename);
        }

        // Update employee data
        return employeeRepo.save(employee);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepo.deleteEmployeeById(id);
    }

    public Employee findEmployee(Long id) {
        return employeeRepo.findEmployeeById(id).orElseThrow(() -> new NotFoundEmployyeExcetion("No Employee with the specified id"));
    }
}
