package hr.mvc.role.entity;

import hr.mvc.employee.entity.EmployeeEntity;
import hr.mvc.project.entity.ProjectEntity;

import javax.persistence.*;

@Table(name = "role")
@Entity
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee")
    private EmployeeEntity employee;

    @ManyToOne
    @JoinColumn(name = "project")
    private ProjectEntity project;

    @Column
    private Boolean manager;


    //getters and setters:


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public Boolean getManager() {
        return manager;
    }

    public void setManager(Boolean manager) {
        this.manager = manager;
    }
}
