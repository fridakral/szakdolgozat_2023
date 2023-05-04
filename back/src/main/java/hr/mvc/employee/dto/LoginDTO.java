package hr.mvc.employee.dto;

import hr.mvc.employee.enums.EmployeeRoleEnum;

public class LoginDTO {

    private Long id;
    private Boolean verified;
    private String username;
    private  String password;
    private EmployeeRoleEnum role;


    public EmployeeRoleEnum getPost() {
        return role;
    }

    public void setPost(EmployeeRoleEnum post) {
        this.role = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
