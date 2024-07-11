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
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeResssource {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeResssource(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    //@PreAuthorize("hasAuthority('ADMIN')") this serves for roles authorization , not the security the endpoints are already secured using the security configuration
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
        Employee employee = employeeService.findEmployee(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);//we always return ResponseEntity containing the data returned and the HttpStatus
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestPart("employee") Employee employee, @RequestPart("file") MultipartFile file) throws IOException {
        Employee addedEmployee = employeeService.addEmployee(employee, file);
        return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
    }
    @PutMapping("/updateEmployee")
    public ResponseEntity<Employee> updateEmployee(@RequestPart("employee") Employee employee, @RequestPart("file") MultipartFile file) throws IOException{
        Employee updatedEmployee = employeeService.updateEmployee(employee, file);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
