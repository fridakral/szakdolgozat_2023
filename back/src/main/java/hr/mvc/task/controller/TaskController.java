package hr.mvc.task.controller;

import hr.config.filters.CustomAuthorizationFilter;
import hr.config.security.SecurityConfig;
import hr.config.security.UserService;
import hr.mvc.task.dto.AddUserToTaskDTO;
import hr.mvc.task.dto.CreateTaskDTO;
import hr.mvc.task.dto.UpdateTaskBasic;
import hr.mvc.task.entity.TaskEntity;
import hr.mvc.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    TaskService taskService;


    @GetMapping("/delete/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id){
        try {
            return ResponseEntity.ok(taskService.deleteTask(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity updateTask(@RequestBody UpdateTaskBasic taskDto){
        try {
            return ResponseEntity.ok(taskService.updateTask(taskDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }


    @PostMapping("/addUsers")
    public ResponseEntity addUsers(@RequestBody AddUserToTaskDTO dto){
        try {
            return ResponseEntity.ok(taskService.addUsersToTask(dto));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }


}
