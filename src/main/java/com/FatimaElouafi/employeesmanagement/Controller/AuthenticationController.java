package com.FatimaElouafi.employeesmanagement.Controller;

import com.FatimaElouafi.employeesmanagement.model.Employee;
import com.FatimaElouafi.employeesmanagement.model.User;
import com.FatimaElouafi.employeesmanagement.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/admin/employee")
public class AuthenticationController {
    @Autowired
    private  final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping("/register")
    public ResponseEntity<ResponseEntity<?>> registrer(@RequestBody User request){

        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseEntity<?>> login(@RequestBody User request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
