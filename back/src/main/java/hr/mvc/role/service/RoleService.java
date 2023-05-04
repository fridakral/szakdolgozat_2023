package hr.mvc.role.service;

import hr.mvc.employee.entity.EmployeeEntity;
import hr.mvc.employee.service.EmployeeService;
import hr.mvc.project.entity.ProjectEntity;
import hr.mvc.employee.dto.UserForProjectSettings;
import hr.mvc.role.dto.AddRoleDto;
import hr.mvc.role.entity.RoleEntity;
import hr.mvc.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EmployeeService employeeService;


    public List<RoleEntity> findByUserId(Long userId){
        List<RoleEntity> roles = roleRepository.findByUserId(userId);
        return roles;
    }
    @Transactional
    public Boolean deleteRolesWithProject(Long projectId){
        return roleRepository.deleteByProject(projectId);
    }

    @Transactional
    public RoleEntity createRole(ProjectEntity projectEntity, Long employeeId, Boolean isManager){
        RoleEntity role = new RoleEntity();
        EmployeeEntity employeeEntity = employeeService.getEmployeeById(employeeId);
        role.setEmployee(employeeEntity);
        role.setProject(projectEntity);
        role.setManager(isManager);
        role = roleRepository.save(role);
        return role;
    }

    public List<UserForProjectSettings> findUsersOnProject(Long projectId) {
        List<UserForProjectSettings> usersForSettings = new ArrayList<>();
        List<RoleEntity> roles = roleRepository.findByProjectId(projectId);
        for(RoleEntity entity : roles){
            UserForProjectSettings userForSettings = new UserForProjectSettings();
            userForSettings.setId(entity.getEmployee().getId());
            userForSettings.setFirstName(entity.getEmployee().getFirstName());
            userForSettings.setLastName(entity.getEmployee().getLastName());
            userForSettings.setUsername(entity.getEmployee().getUsername());
            userForSettings.setManager(entity.getManager());
            usersForSettings.add(userForSettings);
        }
        return usersForSettings;
    }

    @Transactional
    public void removeRole(AddRoleDto roleDto) {
        roleRepository.delete(roleRepository.fidRole(roleDto.getProjectId(), roleDto.getEmployeeId()).getId());
    }
}
