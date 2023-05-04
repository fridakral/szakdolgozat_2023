package hr.mvc.task.service;

import hr.mvc.employee.dto.UserForProjectSettings;
import hr.mvc.employee.entity.EmployeeEntity;
import hr.mvc.employee.service.EmployeeService;
import hr.mvc.list.entity.ListEntity;
import hr.mvc.task.dto.AddUserToTaskDTO;
import hr.mvc.task.dto.TaskAndPosition;
import hr.mvc.task.dto.TaskDTO;
import hr.mvc.task.dto.UpdateTaskBasic;
import hr.mvc.task.entity.TaskEntity;
import hr.mvc.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    EmployeeService employeeService;


    public Integer countTaskInList(Long listId){
        Integer count = taskRepository.countTasksInList(listId);
        return count;
    }


    @Transactional
    public void updateTaskPosition(TaskAndPosition data){
        TaskEntity entity = taskRepository.findById(data.getTaskId());
        entity.setSerial(data.getPosition());
        taskRepository.update(entity);
    }

    public List<TaskEntity> findAllByList(Long listId){
        return taskRepository.findAllByList(listId);
    }

    public List<TaskDTO> getTaskDtosByListId(Long id) {
        List<TaskEntity> taskEntities = findAllByList(id);
        List<TaskDTO> taskDTOS = new ArrayList<>();
        for (TaskEntity taskEntity: taskEntities){
            taskDTOS.add(taskEntityToTaskDto(taskEntity));
        }
        return taskDTOS;
    }

    public TaskEntity getTaskById(Long id){
        return taskRepository.findById(id);
    }


    private TaskDTO taskEntityToTaskDto(TaskEntity taskEntity){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskId(taskEntity.getId());
        taskDTO.setTaskCreatedOn(taskEntity.getCreatedOn());
        taskDTO.setTaskDescription(taskEntity.getDescription());
        taskDTO.setTaskName(taskEntity.getName());
        taskDTO.setUsersOnTask(getUsernamesOnTask(taskEntity));
        taskDTO.setTaskDueDate(taskEntity.getDueDate());
        taskDTO.setSerial(taskEntity.getSerial());
        return taskDTO;
    }

    public List<String> getUsernamesOnTask(TaskEntity taskEntity){

        List<EmployeeEntity> employeeEntities = taskEntity.getEmployee();
        List<String> usernames = new ArrayList<>();

        for(EmployeeEntity employee: employeeEntities){
            usernames.add(employee.getUsername());
        }
        return usernames;
    }

    @Transactional
    public TaskEntity saveTask(TaskEntity entity) {
        Integer tasksInList = taskRepository.countTasksInList(entity.getList().getId());
        if(tasksInList.equals(null)){
            entity.setSerial(1);
        }else{
            entity.setSerial(tasksInList+1);
        }
        TaskEntity newTask = taskRepository.save(entity);
        return newTask;
    }


    @Transactional
    public Boolean deleteTaskWithListId(Long listId){
        List<TaskEntity> tasks = taskRepository.findAllByList(listId);
        for(TaskEntity entity : tasks){
            taskRepository.delete(entity.getId());
        }
        return Boolean.TRUE;
    }
    @Transactional
    public Boolean deleteTask(Long id) {
        Long listId = taskRepository.findById(id).getList().getId();
        Integer serial = taskRepository.findById(id).getSerial();
        taskRepository.delete(id);
        modifySerials(serial, listId);
        return Boolean.TRUE;
    }
    @Transactional
    public void modifySerials(Integer serial, Long listId) {

        for (TaskEntity task: findAllByList(listId)){
            if (task.getSerial()>serial){
                task.setSerial(task.getSerial()-1);
                taskRepository.update(task);
            }
        }
    }

    @Transactional
    public Object updateTask(UpdateTaskBasic taskDto) {
        TaskEntity entity = taskRepository.findById(taskDto.getId());
        entity.setName(taskDto.getName());
        entity.setDueDate(taskDto.getDueDate());
        entity.setDescription(taskDto.getDescription());
        entity = taskRepository.update(entity);
        TaskDTO dto = taskEntityToTaskDto(entity);
        return dto;
    }


    @Transactional
    public List<UserForProjectSettings> addUsersToTask(AddUserToTaskDTO dto) {
        TaskEntity task = taskRepository.findById(dto.getTaskId());

        for (String employeeUsername : dto.getUsernames()){
            task.addEmployee(employeeService.getEmployeeByUsername(employeeUsername));
        }
        taskRepository.update(task);
        return employeeService.entitiesToUserForSettingsDto(taskRepository.findById(dto.getTaskId()).getEmployee());
    }


    @Transactional
    public void moveTask(List<TaskAndPosition> newTasks, Long taskId, ListEntity listEntity){

        for(TaskAndPosition taskAndPosition : newTasks){
            TaskEntity entity = taskRepository.findById(taskAndPosition.getTaskId());
            if(taskAndPosition.getTaskId().equals(taskId)){
                entity.setList(listEntity);
            }
            entity.setSerial(taskAndPosition.getPosition());
            taskRepository.update(entity);
        }

    }

}
