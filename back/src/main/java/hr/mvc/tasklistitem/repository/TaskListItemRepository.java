package hr.mvc.tasklistitem.repository;

import hr.mvc.core.CoreRepository;
import hr.mvc.tasklistitem.entity.TaskListItemEntity;
import org.springframework.stereotype.Repository;

@Repository
public class TaskListItemRepository extends CoreRepository<TaskListItemEntity> {


    @Override
    protected Class<TaskListItemEntity> getManagedClass() {
        return TaskListItemEntity.class;
    }
}
