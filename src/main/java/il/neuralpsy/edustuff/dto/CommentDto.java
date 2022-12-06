package il.neuralpsy.edustuff.dto;

import il.neuralpsy.edustuff.model.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;
@Data
public class CommentDto {
    @Id
    @GeneratedValue
    private Integer commentId;
    private UserDto user;
    private String text;
    private Timestamp timestamp;
}
