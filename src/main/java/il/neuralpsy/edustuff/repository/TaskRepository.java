package il.neuralpsy.edustuff.repository;

import il.neuralpsy.edustuff.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
