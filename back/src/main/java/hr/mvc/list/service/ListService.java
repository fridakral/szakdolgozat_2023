package hr.mvc.list.service;

import hr.mvc.employee.entity.EmployeeEntity;
import hr.mvc.list.dto.BasicListDTO;
import hr.mvc.list.dto.ListDTO;
import hr.mvc.list.dto.MoveTaskBetweenListsDTO;
import hr.mvc.list.dto.MoveTaskInListDTO;
import hr.mvc.list.entity.ListEntity;
import hr.mvc.list.repository.ListRepository;
import hr.mvc.project.entity.ProjectEntity;
import hr.mvc.task.dto.CreateTaskDTO;
import hr.mvc.task.dto.TaskAndPosition;
import hr.mvc.task.dto.TaskDTO;
import hr.mvc.task.entity.TaskEntity;
import hr.mvc.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ListService {

    @Autowired
    ListRepository listRepository;

    @Autowired
    TaskService taskService;


    public List<ListEntity> findByProjectId(Long projectId){
        return listRepository.findByProjectId(projectId);
    }

    public List<BasicListDTO> createListDtos(List<ListEntity> lists){
        List<BasicListDTO> basicListDTOs = new ArrayList<>();
        for(ListEntity listEntity : lists){
            basicListDTOs.add(listEntityToBasicListDto(listEntity));
        }
        return basicListDTOs;
    }


    private BasicListDTO listEntityToBasicListDto(ListEntity listEntity){
        BasicListDTO listDTO = new BasicListDTO();
        listDTO.setId(listEntity.getId());
        listDTO.setName(listEntity.getName());
        listDTO.setTaskCount(taskService.countTaskInList(listEntity.getId()));
        listDTO.setSerial(listEntity.getNumber());
        return listDTO;
    }

    public void createBasicLists(ProjectEntity projectEntity) {
        createList("Tasks", 1, projectEntity);
        createList("In Progress", 2, projectEntity);
        createList("Done", 3, projectEntity);
    }

    @Transactional
    public void createList(String listName, Integer listNumber, ProjectEntity projectEntity){
        ListEntity list = new ListEntity();
        list.setName(listName);
        list.setNumber(listNumber);
        list.setProject(projectEntity);
        listRepository.save(list);
    }


    public List<ListDTO> getListDtosByProjectId(Long projectId){
        List<ListEntity> listEntities = findByProjectId(projectId);

        List<ListDTO> listDTOS = new ArrayList<>();
        for(ListEntity listEntity: listEntities){
            listDTOS.add(listEntityToListDTO(listEntity));
        }
        return listDTOS;
    }

    private ListDTO listEntityToListDTO(ListEntity listEntity){
        ListDTO listDTO = new ListDTO();
        listDTO.setListId(listEntity.getId());
        listDTO.setListName(listEntity.getName());
        listDTO.setSerial(listEntity.getNumber());
        listDTO.setTasks(taskService.getTaskDtosByListId(listEntity.getId()));
        return listDTO;
    }


    public TaskDTO saveTask(CreateTaskDTO taskDTO) {
        TaskEntity entity = new TaskEntity();
        entity.setCreatedOn(taskDTO.getCreatedOn());
        entity.setDescription(taskDTO.getDescription());
        entity.setDueDate(taskDTO.getDueDate());
        entity.setName(taskDTO.getName());
        entity.setList(listRepository.findById(taskDTO.getListId()));
        TaskEntity newTask = taskService.saveTask(entity);
        TaskDTO dto = new TaskDTO();
        dto.setTaskDueDate(newTask.getDueDate());
        dto.setSerial(newTask.getSerial());
        dto.setTaskId(newTask.getId());
        if(entity.getEmployee()!=null){
            dto.setUsersOnTask(getUsernames(entity.getEmployee()));
        }
        dto.setTaskDescription(entity.getDescription());
        dto.setTaskName(entity.getName());
        dto.setTaskDueDate(entity.getDueDate());
        return dto;
    }

    @Transactional
    public Boolean deleteListsWithProjectId(Long projectId){
        List<ListEntity> lists = listRepository.findByProjectId(projectId);
        for(ListEntity entity : lists){
            taskService.deleteTaskWithListId(entity.getId());
            listRepository.delete(entity.getId());
        }
        return Boolean.TRUE;
    }

    private List<String> getUsernames(List<EmployeeEntity> entities){
        List<String> usernames = new ArrayList<>();
        for(EmployeeEntity entity: entities){
            usernames.add(entity.getUsername());
        }
        return usernames;

    }

    @Transactional
    public Object moveTaskInList(MoveTaskInListDTO dto) {
        for(TaskAndPosition data : dto.getTasksAndPositions()){
            taskService.updateTaskPosition(data);
        }
        return null;
    }


    public List<ListDTO> moveTask(MoveTaskBetweenListsDTO dto) {

        taskService.moveTask(dto.getAfterTasks(), dto.getMovedTaskId(), listRepository.findById(dto.getAfterListId()));
        taskService.modifySerials(taskService.getTaskById(dto.getMovedTaskId()).getSerial(), dto.getBeforeListId());

        return getListDtosByProjectId(listRepository.findById(dto.getAfterListId()).getProject().getId());
    }


}
