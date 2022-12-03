package il.neuralpsy.edustuff.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class TaskStatus {
    @Id
    private Integer taskStatusId;
    @Column(unique = true)
    private String name;
}
