package hr.mvc.employee.repository;

import hr.mvc.core.CoreRepository;
import hr.mvc.employee.entity.EmployeeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository extends CoreRepository<EmployeeEntity> {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected Class<EmployeeEntity> getManagedClass() {
        return EmployeeEntity.class;
    }

    public EmployeeEntity findByUsername(String username){
        EmployeeEntity entity = new EmployeeEntity();
        for(EmployeeEntity employee : findAll()){
            if(employee.getUsername().equals(username)){
                entity = employee;
            }
        }
        return entity;
    }

    public List<EmployeeEntity> findAllUnverified(){
        List<EmployeeEntity> entities = new ArrayList<>();
        for (EmployeeEntity entity : findAll()){
            if(entity.getVerified()==Boolean.FALSE){
                entities.add(entity);
            }
        }
        return entities;
    }

    public EmployeeEntity insertEmployee(EmployeeEntity entity){
        this.em.persist(entity);
        return entity;
    }
}
