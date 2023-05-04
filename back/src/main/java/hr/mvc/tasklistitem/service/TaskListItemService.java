package hr.mvc.tasklistitem.service;

import hr.mvc.tasklistitem.repository.TaskListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskListItemService {

    @Autowired
    TaskListItemRepository taskListItemRepository;




}
