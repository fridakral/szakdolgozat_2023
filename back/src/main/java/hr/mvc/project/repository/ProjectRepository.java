package hr.mvc.project.repository;

import hr.mvc.core.CoreRepository;
import hr.mvc.project.entity.ProjectEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository extends CoreRepository<ProjectEntity> {

    @Override
    protected Class<ProjectEntity> getManagedClass() {
        return ProjectEntity.class;
    }
}
