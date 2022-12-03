package il.neuralpsy.edustuff.repository;

import il.neuralpsy.edustuff.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
