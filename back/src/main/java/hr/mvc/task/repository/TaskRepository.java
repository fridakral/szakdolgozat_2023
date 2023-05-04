package hr.mvc.task.repository;

import hr.mvc.core.CoreRepository;
import hr.mvc.employee.entity.EmployeeEntity;
import hr.mvc.list.entity.ListEntity;
import hr.mvc.task.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.*;

@Repository
public class TaskRepository extends CoreRepository<TaskEntity> {

    @Autowired
    EntityManager em;

    @Override
    protected Class<TaskEntity> getManagedClass() {
        return TaskEntity.class;
    }


    public Integer countTasksInList(Long listId){
        Integer count = 0;
        for (TaskEntity task : findAll()){
            if (task.getList().getId().equals(listId)) count++;
        }
        return count;
    }

    public List<TaskEntity> findAllByList(Long listId){
        List<TaskEntity> tasks = new ArrayList<>();
        for (TaskEntity task : findAll()){
            if (task.getList().getId().equals(listId)) tasks.add(task);

        }
        Comparator<TaskEntity> compareById =
                (TaskEntity o1, TaskEntity o2) -> o1.getSerial().compareTo( o2.getSerial() );

        Collections.sort(tasks, compareById);

        return tasks;
    }

}
