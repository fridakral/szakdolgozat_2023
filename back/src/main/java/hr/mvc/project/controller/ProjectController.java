package hr.mvc.project.controller;

import hr.mvc.project.dto.UpdateProject;
import hr.mvc.project.entity.ProjectEntity;
import hr.mvc.project.service.ProjectService;
import hr.mvc.role.dto.AddRoleDto;
import hr.mvc.role.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;


    @GetMapping("/findAll/{id}")
    public ResponseEntity findAll(@PathVariable Long id){
        try{
            return ResponseEntity.ok(projectService.findAll(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }


    @PostMapping("/create/{id}")
    public  ResponseEntity createProject(@RequestBody ProjectEntity projectEntity, @PathVariable Long id){
        try {
            return ResponseEntity.ok(projectService.create(projectEntity, id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/getProject/{projectId}")
    public ResponseEntity getDetailedProjectDTO(@PathVariable Long projectId){
        try {
            return ResponseEntity.ok(projectService.getDetailedProjectDTO(projectId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/addEmployeeToProject")
    public ResponseEntity addEmployeeToProject(@RequestBody AddRoleDto roleDto){
        try {
            return ResponseEntity.ok(projectService.addEmployeeToProject(roleDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/removeEmployee")
    public ResponseEntity removeEmployeeFromProject(@RequestBody AddRoleDto roleDto){
        try {
            return ResponseEntity.ok(projectService.removeEmployee(roleDto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id){
        try {
            return ResponseEntity.ok(projectService.deleteProject(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/settings/{projectId}")
    public ResponseEntity getSettingsData(@PathVariable Long projectId){
        try {
            return ResponseEntity.ok(projectService.getSettingsData(projectId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity updateProject(@PathVariable UpdateProject dto){
        try {
            return ResponseEntity.ok(projectService.update(dto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }


}
