package hr.mvc.tasklist.repository;

import hr.mvc.core.CoreRepository;
import hr.mvc.tasklist.entity.TaskListEntity;
import org.springframework.stereotype.Repository;

@Repository
public class TaskListRepository extends CoreRepository<TaskListEntity> {

    @Override
    protected Class<TaskListEntity> getManagedClass() {
        return TaskListEntity.class;
    }
}
