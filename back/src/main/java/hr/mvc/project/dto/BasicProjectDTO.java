package hr.mvc.project.dto;

import hr.mvc.list.dto.BasicListDTO;

import java.util.List;

public class BasicProjectDTO {

    private Boolean isManager;

    private Long id;

    private String name;

    private List<BasicListDTO> lists;

    public Boolean getManager() {
        return isManager;
    }

    public void setManager(Boolean manager) {
        isManager = manager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BasicListDTO> getLists() {
        return lists;
    }

    public void setLists(List<BasicListDTO> lists) {
        this.lists = lists;
    }
}
