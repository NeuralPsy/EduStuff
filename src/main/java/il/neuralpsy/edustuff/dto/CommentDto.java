package il.neuralpsy.edustuff.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

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
        return o.timestamp.compareTo(this.timestamp);
    }
}
