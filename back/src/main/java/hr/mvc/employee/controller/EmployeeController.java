package hr.mvc.employee.controller;

import hr.mvc.employee.dto.ChangePasswordDTO;
import hr.mvc.employee.dto.EmployeeDataDTO;
import hr.mvc.employee.dto.RegisterDTO;
import hr.mvc.employee.dto.UserDataDTO;
import hr.mvc.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        try {
            return ResponseEntity.ok(employeeService.getAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO dto){
        try{
            Boolean userExist = employeeService.isExist(dto.getUsername());
            if(userExist==Boolean.FALSE){
                employeeService.register(dto);
                return ResponseEntity.ok("Regisztráció elküldve.");
            }
            else return ResponseEntity.status(HttpStatus.CONFLICT).body("Username "+ dto.getUsername() + " is already exist");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/getEmployee/{username}")
    public ResponseEntity<?> getEmployee(@PathVariable String username){
        try{
            return ResponseEntity.ok(employeeService.GetLoginDTOByUsername(username));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/getDetailedUser/{userId}")
    public ResponseEntity<?> getDetailedUser(@PathVariable Long userId){
        try{
            return ResponseEntity.ok(employeeService.getDetailedUser(userId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/deleteUser/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        try{
            employeeService.deleteUser(userId);
            return ResponseEntity.ok(userId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/verifyUser/{userId}")
    public ResponseEntity<?> verifyUser(@PathVariable Long userId){
        try {
            employeeService.verifyUser(userId);
            return ResponseEntity.ok(userId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/updateUser")
    public ResponseEntity updateUserData(@RequestBody UserDataDTO employee){
        try {
            return ResponseEntity.ok(employeeService.updateUserData(employee));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/updateEmployee")
    public ResponseEntity updateEmployeeData(@RequestBody EmployeeDataDTO employee){
        try {
            return ResponseEntity.ok(employeeService.updateEmployeeData(employee));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity changePassword(@RequestBody ChangePasswordDTO dto){
        try {
            return ResponseEntity.ok(employeeService.changePassword(dto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }




}
