package il.neuralpsy.edustuff.repository;

import il.neuralpsy.edustuff.model.Task;
import il.neuralpsy.edustuff.model.TaskStatus;
import il.neuralpsy.edustuff.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;

@Component
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Collection<Task> findTasksByUser_UserId(Integer userId);

    @Transactional
    @Modifying
    @Query("update Task set taskStatus = :taskStatus where taskId = :taskId")
    void updateTaskStatus(@Param("taskStatus") TaskStatus taskStatus, @Param("taskId") Integer taskId);

    @Transactional
    @Modifying
    @Query("update Task set user = :user, startTime = :startTime where taskId = :taskId")
    void putUserIntoTask(@Param("user") User user, @Param("startTime") LocalDateTime startTime,
                         @Param("taskId") Integer taskId);

    @Transactional
    @Modifying
    @Query("update Task set taskStatus = :taskStatus, startTime = :startTime where taskId = :taskId")
    void setTaskStatus(@Param("taskStatus") TaskStatus taskStatus, @Param("startTime") LocalDateTime startTime,
                       @Param("taskId") Integer taskId);
}
