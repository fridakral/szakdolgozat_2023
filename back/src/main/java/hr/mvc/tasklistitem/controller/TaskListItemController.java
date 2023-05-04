package hr.mvc.tasklistitem.controller;

import hr.mvc.tasklistitem.service.TaskListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tasklistitem")
public class TaskListItemController {

    @Autowired
    TaskListItemService taskListItemService;
}
