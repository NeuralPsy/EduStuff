package il.neuralpsy.edustuff.repository;

import il.neuralpsy.edustuff.model.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Transactional
    @Modifying
    @Query("update Comment set timestamp = :timeStamp, isEdited = :isEdited, text = :text where commentId = :commentId")
    void updateComment(@Param("timeStamp")Timestamp timestamp, @Param("isEdited") Boolean isEdited,
                       @Param("text") String text, @Param("commentId") Integer commentId);
}