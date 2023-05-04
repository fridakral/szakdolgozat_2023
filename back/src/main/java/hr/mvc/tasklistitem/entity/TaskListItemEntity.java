package hr.mvc.tasklistitem.entity;

import hr.mvc.tasklist.entity.TaskListEntity;

import javax.persistence.*;

@Table(name = "Tasklistitem")
@Entity
public class TaskListItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Boolean isDone;

    @Column
    private String name;


    @ManyToOne
    private TaskListEntity taskList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskListEntity getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskListEntity taskList) {
        this.taskList = taskList;
    }
}
