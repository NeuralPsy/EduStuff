package il.neuralpsy.edustuff.repository;

import il.neuralpsy.edustuff.model.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

@Component
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Collection<Comment> findAllByTask_TaskId(Integer taskId);

    Collection<Comment> findAllByUser_UserId(Integer userId);
}
