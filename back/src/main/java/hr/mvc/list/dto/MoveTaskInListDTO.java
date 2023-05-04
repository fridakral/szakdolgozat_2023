package hr.mvc.list.dto;

import hr.mvc.task.dto.TaskAndPosition;

import java.util.List;

public class MoveTaskInListDTO {

    private Long listId;

    private List<TaskAndPosition> tasksAndPositions;

    public Long getListId() {
        return listId;
    }

    public void setListId(Long listId) {
        this.listId = listId;
    }

    public List<TaskAndPosition> getTasksAndPositions() {
        return tasksAndPositions;
    }

    public void setTasksAndPositions(List<TaskAndPosition> tasksAndPositions) {
        this.tasksAndPositions = tasksAndPositions;
    }
}
