package hr.mvc.task.dto;

import java.util.Date;
import java.util.List;

public class TaskDTO {

    private Integer serial;

    private String taskName;

    private Long taskId;
    private Date taskDueDate;
    private Date taskCreatedOn;
    private String taskDescription;
    private List<String> usersOnTask;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Date getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(Date taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public Date getTaskCreatedOn() {
        return taskCreatedOn;
    }

    public void setTaskCreatedOn(Date taskCreatedOn) {
        this.taskCreatedOn = taskCreatedOn;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public List<String> getUsersOnTask() {
        return usersOnTask;
    }

    public void setUsersOnTask(List<String> usersOnTask) {
        this.usersOnTask = usersOnTask;
    }
}
