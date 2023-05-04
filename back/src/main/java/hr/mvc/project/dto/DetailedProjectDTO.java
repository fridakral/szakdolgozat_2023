package hr.mvc.project.dto;

import hr.mvc.list.dto.ListDTO;
import hr.mvc.list.entity.ListEntity;
import hr.mvc.project.entity.ProjectEntity;

import java.util.Date;
import java.util.List;

public class DetailedProjectDTO {

    private Long projectID;
    private String projectName;
    private String projectDescription;
    private Date projectCreatedOn;
    private Date projectDueDate;
    private List<ListDTO> lists;


    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Date getProjectCreatedOn() {
        return projectCreatedOn;
    }

    public void setProjectCreatedOn(Date projectCreatedOn) {
        this.projectCreatedOn = projectCreatedOn;
    }

    public Date getProjectDueDate() {
        return projectDueDate;
    }

    public void setProjectDueDate(Date projectDueDate) {
        this.projectDueDate = projectDueDate;
    }

    public List<ListDTO> getLists() {
        return lists;
    }

    public void setLists(List<ListDTO> lists) {
        this.lists = lists;
    }
}
