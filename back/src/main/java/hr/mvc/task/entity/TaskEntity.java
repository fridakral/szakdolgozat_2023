package hr.mvc.task.entity;

import hr.mvc.employee.entity.EmployeeEntity;
import hr.mvc.list.entity.ListEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "Task")
@Entity
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer serial;

    @ManyToOne
    private ListEntity list;

    @Column
    private String name;

    @Column
    private Date dueDate;

    @Column
    private Date createdOn;

    @Column
    private String description;

    @ManyToMany
    private List<EmployeeEntity> employee;


    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ListEntity getList() {
        return list;
    }

    public void setList(ListEntity list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EmployeeEntity> getEmployee() {
        return employee;
    }

    public void setEmployee(List<EmployeeEntity> employee) {
        this.employee = employee;
    }

    public void addEmployee(EmployeeEntity e){
        this.employee.add(e);
    }
}
