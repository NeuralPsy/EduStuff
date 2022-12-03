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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")})
public class TaskStatus {
    @Id
    private Integer taskStatusId;
    private String name;
}
