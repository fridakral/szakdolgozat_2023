package hr.mvc.tasklist.controller;

import hr.mvc.tasklist.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tasklist")
public class TaskListController {

    @Autowired
    TaskListService taskListService;

}
