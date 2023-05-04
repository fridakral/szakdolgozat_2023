package hr.mvc.task.dto;

import java.util.List;

public class AddUserToTaskDTO {
    private Long taskId;
    private List<String> usernames;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }
}
