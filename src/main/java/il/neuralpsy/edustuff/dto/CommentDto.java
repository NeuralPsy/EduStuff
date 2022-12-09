package il.neuralpsy.edustuff.dto;

import il.neuralpsy.edustuff.model.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class CommentDto {
    @Id
    @GeneratedValue
    private Integer commentId;
    private UserDto user;
    private String text;
    private LocalDateTime timestamp;
}
