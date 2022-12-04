package il.neuralpsy.edustuff.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="subjects")
    private User user;


    public Subject(Integer subjectId, String name){
        this.subjectId = subjectId;
        this.name = name;
    }
}
