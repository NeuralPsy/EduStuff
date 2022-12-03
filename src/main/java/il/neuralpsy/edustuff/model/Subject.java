package il.neuralpsy.edustuff.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")})
public class Subject {
    @Id
    private Integer subjectId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "subjects")
    private User user;
}
