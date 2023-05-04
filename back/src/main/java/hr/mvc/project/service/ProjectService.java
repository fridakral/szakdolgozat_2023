package hr.mvc.project.service;

import hr.config.security.UserService;
import hr.mvc.employee.enums.EmployeeRoleEnum;
import hr.mvc.employee.service.EmployeeService;
import hr.mvc.list.service.ListService;
import hr.mvc.project.dto.BasicProjectDTO;
import hr.mvc.project.dto.DetailedProjectDTO;
import hr.mvc.project.dto.ProjectSettingsDTO;
import hr.mvc.project.dto.UpdateProject;
import hr.mvc.project.entity.ProjectEntity;
import hr.mvc.project.repository.ProjectRepository;
import hr.mvc.role.dto.AddRoleDto;
import hr.mvc.role.entity.RoleEntity;
import hr.mvc.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    ListService listService;


    public List<BasicProjectDTO> findAll(Long id){
        List<BasicProjectDTO> projects = new ArrayList<>();
        List<RoleEntity> roles = roleService.findByUserId(id);
        for(RoleEntity role: roles){
            projects.add(roleEntityToBasicProjectDto(role));
        }
        return  projects;
    }

    private BasicProjectDTO roleEntityToBasicProjectDto(RoleEntity role){
        BasicProjectDTO project = new BasicProjectDTO();
        project.setName(role.getProject().getName());
        project.setLists(listService.createListDtos(listService.findByProjectId(role.getProject().getId())));
        project.setId(role.getProject().getId());
        project.setManager(role.getManager());
        return project;
    }

    @Transactional
    public ProjectEntity create(ProjectEntity projectEntity, Long userId){
        projectEntity.setCreatedOn(new Date());
        roleService.createRole(projectEntity, userId, Boolean.TRUE);
        projectRepository.save(projectEntity);
        listService.createBasicLists(projectEntity);
        return projectRepository.findById(projectEntity.getId());
    }

    public RoleEntity addEmployeeToProject(AddRoleDto role){
        if(role.getEmployeeRoleEnum().equals(EmployeeRoleEnum.ROLE_EMPLOYEE)){
            return roleService.createRole(projectRepository.findById(role.getProjectId()), role.getEmployeeId(), Boolean.FALSE);
        }else return roleService.createRole(projectRepository.findById(role.getProjectId()), role.getEmployeeId(), Boolean.TRUE);
    }


    public DetailedProjectDTO getDetailedProjectDTO(Long projectId) {
        DetailedProjectDTO projectDTO = new DetailedProjectDTO();
        ProjectEntity myProject = projectRepository.findById(projectId);
        projectDTO.setProjectID(projectId);
        projectDTO.setProjectName(myProject.getName());
        projectDTO.setProjectCreatedOn(myProject.getCreatedOn());
        projectDTO.setProjectDescription(myProject.getDescription());
        projectDTO.setProjectDueDate(myProject.getDueDate());
        projectDTO.setLists(listService.getListDtosByProjectId(projectId));
        return projectDTO;
    }


    @Transactional
    public Boolean deleteProject(Long id){
        projectRepository.delete(id);
        roleService.deleteRolesWithProject(id);
        listService.deleteListsWithProjectId(id);
        return Boolean.TRUE;
    }

    public ProjectSettingsDTO getSettingsData(Long projectId) {
        ProjectSettingsDTO projectDTO = new ProjectSettingsDTO();
        projectDTO.setUsers(roleService.findUsersOnProject(projectId));
        ProjectEntity projectEntity = projectRepository.findById(projectId);
        projectDTO.setDescription(projectEntity.getDescription());
        projectDTO.setDueDate(projectEntity.getDueDate());
        projectDTO.setId(projectId);
        projectDTO.setName(projectEntity.getName());
        projectDTO.setCreatedOn(projectEntity.getCreatedOn());
        return projectDTO;
    }

    public Boolean removeEmployee(AddRoleDto roleDto) {
        roleService.removeRole(roleDto);
        return Boolean.TRUE;
    }

    @Transactional
    public Object update(UpdateProject dto) {
        ProjectEntity entity = projectRepository.findById(dto.getId());
        entity.setDueDate(dto.getDueDate());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        projectRepository.update(entity);
        return projectRepository.findById(dto.getId());
    }
}
