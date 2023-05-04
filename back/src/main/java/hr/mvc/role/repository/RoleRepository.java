package hr.mvc.role.repository;

import hr.mvc.core.CoreRepository;
import hr.mvc.role.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleRepository extends CoreRepository<RoleEntity> {

    @Autowired
    EntityManager em;

    @Override
    protected Class<RoleEntity> getManagedClass() {
        return RoleEntity.class;
    }


    public List<RoleEntity> findByUserId(Long userId){
        List<RoleEntity> roles = new ArrayList<>();
        for(RoleEntity role : findAll()){
            if(role.getEmployee().getId().equals(userId)){
                roles.add(role);
            }
        }
        return roles;
    }

    public List<RoleEntity> findByProjectId(Long projectId){
        List<RoleEntity> roles = new ArrayList<>();
        for(RoleEntity entity : findAll()){
            if(entity.getProject().getId().equals(projectId)){
                roles.add(entity);
            }
        }
        return roles;
    }

    public RoleEntity fidRole(Long projectId, Long employeeId){
        List<RoleEntity> rolesOnProject = findByProjectId(projectId);
        RoleEntity role = new RoleEntity();
        for(RoleEntity roleEntity: rolesOnProject){
            if(roleEntity.getEmployee().getId().equals(employeeId))role=roleEntity;
        }
        return role;
    }

    public Boolean deleteByProject(Long projectId){
        for(RoleEntity role : findAll()){
            if(role.getProject().getId().equals(projectId)){
                delete(role.getId());
            }
        }
        return Boolean.TRUE;
    }
}
