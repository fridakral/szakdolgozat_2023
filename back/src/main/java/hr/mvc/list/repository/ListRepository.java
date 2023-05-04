package hr.mvc.list.repository;

import hr.mvc.core.CoreRepository;
import hr.mvc.employee.entity.EmployeeEntity;
import hr.mvc.list.entity.ListEntity;
import hr.mvc.role.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ListRepository extends CoreRepository<ListEntity> {


    @Override
    protected Class<ListEntity> getManagedClass() {
        return ListEntity.class;
    }


    public List<ListEntity> findByProjectId(Long projectId){
        List<ListEntity> listEntities = new ArrayList<>();
        for (ListEntity listEntity: findAll()){
            if(listEntity.getProject().getId().equals(projectId)) listEntities.add(listEntity);
        }
        return listEntities;
    }

}
