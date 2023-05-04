package hr.mvc.list.controller;

import hr.mvc.list.dto.MoveTaskBetweenListsDTO;
import hr.mvc.list.dto.MoveTaskInListDTO;
import hr.mvc.list.service.ListService;
import hr.mvc.task.dto.CreateTaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/list")
public class ListController {

    @Autowired
    ListService listService;


    @PostMapping("/createTask")
    public ResponseEntity createTask(@RequestBody CreateTaskDTO taskDTO){
        try{
            return ResponseEntity.ok().body(listService.saveTask(taskDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("/moveTaskInList")
    public ResponseEntity moveTaskInList(@RequestBody MoveTaskInListDTO dto){
        try {
            return ResponseEntity.ok(listService.moveTaskInList(dto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping("moveTaskBetweenLists")
    public ResponseEntity moveTask(@RequestBody MoveTaskBetweenListsDTO dto){
        try {
            return ResponseEntity.ok(listService.moveTask(dto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

}
