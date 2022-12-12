package il.neuralpsy.edustuff.repository;

import il.neuralpsy.edustuff.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Collection<Comment> findAllByTask_TaskId(Integer taskId);

    Collection<Comment> findAllByUser_UserId(Integer userId);
}
