package il.neuralpsy.edustuff.repository;

import il.neuralpsy.edustuff.model.Task;
import il.neuralpsy.edustuff.model.TaskStatus;
import il.neuralpsy.edustuff.model.User;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.CloseableThreadContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;

@Component
public interface TaskRepository extends JpaRepository<Task, Integer> {

//    @Query(value = "select * from Task where user_id = ?1", nativeQuery = true)
    Collection<Task> findTasksByUser_UserId(Integer userId);

    @Transactional
    @Modifying
    @Query("update Task set taskStatus = :taskStatus where taskId = :taskId")
    void updateTaskStatus(@Param("taskStatus") TaskStatus taskStatus, @Param("taskId") Integer taskId);

    @Transactional
    @Modifying
    @Query("update Task set name = :name, startTime = :startTime, taskStatus = :taskStatus where taskId = :taskId")
    void updateTask(@Param("name") String name, @Param("startTime") Timestamp startTime,
                    @Param("taskStatus") TaskStatus taskStatus, @Param("taskId") Integer taskId);

    @Transactional
    @Modifying
    @Query("update Task set user = :user, startTime = :startTime where taskId = :taskId")
    void putUserIntoTask(@Param("user") User user, @Param("startTime") Timestamp startTime,
                         @Param("taskId") Integer taskId);
}
