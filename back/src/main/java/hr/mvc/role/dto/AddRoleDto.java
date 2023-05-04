package hr.mvc.role.dto;

import hr.mvc.employee.enums.EmployeeRoleEnum;

public class AddRoleDto {


    private Long employeeId;

    private Long projectId;

    private EmployeeRoleEnum employeeRoleEnum;


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public EmployeeRoleEnum getEmployeeRoleEnum() {
        return employeeRoleEnum;
    }

    public void setEmployeeRoleEnum(EmployeeRoleEnum employeeRoleEnum) {
        this.employeeRoleEnum = employeeRoleEnum;
    }
}
