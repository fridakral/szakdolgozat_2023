package hr.mvc.project.dto;

import hr.mvc.employee.dto.UserForProjectSettings;

import java.util.Date;
import java.util.List;

public class ProjectSettingsDTO {

    private Long id;
    private String description;
    private Date dueDate;
    private Date createdOn;
    private String name;
    private List<UserForProjectSettings> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserForProjectSettings> getUsers() {
        return users;
    }

    public void setUsers(List<UserForProjectSettings> users) {
        this.users = users;
    }
}
