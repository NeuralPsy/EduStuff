package il.neuralpsy.edustuff.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@SuppressWarnings("ALL")
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
   @ManyToOne
   @JoinColumn(name = "tasks")
    private Task task;
    private String text;
    private LocalDateTime timestamp;

    @Override
    public int compareTo(Comment o) {
        return this.timestamp.compareTo(o.timestamp);
    }
}
