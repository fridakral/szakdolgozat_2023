package hr.mvc.list.dto;

import hr.mvc.task.dto.TaskDTO;

import java.util.List;

public class ListDTO {


    private Long listId;
    private String listName;
    private Integer serial;
    private List<TaskDTO> tasks;


    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
