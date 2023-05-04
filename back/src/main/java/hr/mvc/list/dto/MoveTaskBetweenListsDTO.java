package hr.mvc.list.dto;

import hr.mvc.task.dto.TaskAndPosition;

import java.util.List;

public class MoveTaskBetweenListsDTO {

    private Long beforeListId;

    private Long afterListId;

    private List<TaskAndPosition> beforeTasks;

    private List<TaskAndPosition> afterTasks;

    private Long movedTaskId;


    public Long getBeforeListId() {
        return beforeListId;
    }

    public void setBeforeListId(Long beforeListId) {
        this.beforeListId = beforeListId;
    }

    public Long getAfterListId() {
        return afterListId;
    }

    public void setAfterListId(Long afterListId) {
        this.afterListId = afterListId;
    }

    public List<TaskAndPosition> getBeforeTasks() {
        return beforeTasks;
    }

    public void setBeforeTasks(List<TaskAndPosition> beforeTasks) {
        this.beforeTasks = beforeTasks;
    }

    public List<TaskAndPosition> getAfterTasks() {
        return afterTasks;
    }

    public void setAfterTasks(List<TaskAndPosition> afterTasks) {
        this.afterTasks = afterTasks;
    }

    public Long getMovedTaskId() {
        return movedTaskId;
    }

    public void setMovedTaskId(Long movedTaskId) {
        this.movedTaskId = movedTaskId;
    }
}
