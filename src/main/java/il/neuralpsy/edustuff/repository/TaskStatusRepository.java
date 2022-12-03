package il.neuralpsy.edustuff.repository;

import il.neuralpsy.edustuff.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Integer> {
}
