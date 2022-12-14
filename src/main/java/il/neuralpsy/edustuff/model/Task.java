package il.neuralpsy.edustuff.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@SuppressWarnings("ALL")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Task {
    @Id
    @GeneratedValue
    private Integer taskId;
    private String name;
    @OneToOne
    @JoinColumn(referencedColumnName = "subjectId")
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    private String content;
    private LocalDateTime startTime;
    @OneToOne
    @JoinColumn(referencedColumnName = "taskStatusId")
    private TaskStatus taskStatus;
    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE)
    private Set<Comment> comments;

}
