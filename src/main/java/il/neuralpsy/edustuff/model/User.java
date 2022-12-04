package il.neuralpsy.edustuff.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")})
public class User{
    @Id
    @GeneratedValue
    private Integer userId;
    private String name;
    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;
    @OneToOne
    @JoinColumn(referencedColumnName = "userTypeId")
    private UserType userType;
    private LocalDate birthdate;
    @OneToMany(mappedBy = "user")
    private Set<Task> tasks;
    @OneToMany(mappedBy = "user", orphanRemoval = true)
//    @JoinColumn(referencedColumnName = "user")
    private List<Subject> subjects;

    public void addTask(Task task){
        tasks.add(task);
        task.setUser(this);

    }

    public void removeTask(Task task){
        tasks.remove(task);
        task.setUser(null);
    }



}
