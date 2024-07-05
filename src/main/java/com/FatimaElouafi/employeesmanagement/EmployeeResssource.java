package com.FatimaElouafi.employeesmanagement;

import com.FatimaElouafi.employeesmanagement.model.Employee;
import com.FatimaElouafi.employeesmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController //to indicates that is's a controlle r class
@RequestMapping("/admin/employee") //the requests to intercept by this contorller
public class EmployeeResssource {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeResssource(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
        Employee employee = employeeService.findEmployee(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);//we always return ResponseEntity containing the data returned and the HttpStatus
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestPart("employee") Employee employee, @RequestPart("file") MultipartFile file) throws IOException {
        Employee addedEmployee = employeeService.addEmployee(employee, file);
        return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/updateEmployee")
    public ResponseEntity<Employee> updateEmployee(@RequestPart("employee") Employee employee, @RequestPart("file") MultipartFile file) throws IOException{
        Employee updatedEmployee = employeeService.updateEmployee(employee, file);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
