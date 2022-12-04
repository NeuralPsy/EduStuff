package il.neuralpsy.edustuff.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Comment implements Comparable<Comment>{
    @Id
    @GeneratedValue
    private Integer commentId;
    @ManyToOne
    @JoinColumn(name = "comments")
    private User user;
    private String text;
    private Timestamp timestamp;
    private boolean isEdited;

    @Override
    public int compareTo(Comment o) {
        return this.timestamp.compareTo(o.timestamp);
    }
}
