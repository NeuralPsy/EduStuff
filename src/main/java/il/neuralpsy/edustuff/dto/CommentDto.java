package il.neuralpsy.edustuff.dto;

import il.neuralpsy.edustuff.model.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class CommentDto implements Comparable<CommentDto>{
    @Id
    @GeneratedValue
    private Integer commentId;
    private UserDto user;
    private String text;
    private LocalDateTime timestamp;

    @Override
    public int compareTo(CommentDto o) {
        return this.timestamp.compareTo(o.timestamp);
    }
}
