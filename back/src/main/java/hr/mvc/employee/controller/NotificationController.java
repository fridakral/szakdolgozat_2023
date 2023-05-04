package hr.mvc.employee.controller;

import hr.mvc.employee.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("/unverified")
    public ResponseEntity<?> getEmployee(){
        try{
            return ResponseEntity.ok(notificationService.getAllNotification());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
