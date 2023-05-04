package hr.mvc.tasklist.service;

import hr.mvc.tasklist.repository.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskListService {
    @Autowired
    TaskListRepository taskListRepository;





}
