package il.neuralpsy.edustuff.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Task {
    @Id
    @GeneratedValue
    private Integer taskId;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    private String name;
    private LocalDateTime startTime;
    @OneToOne
    @PrimaryKeyJoinColumn
    private TaskStatus taskStatus;
}
