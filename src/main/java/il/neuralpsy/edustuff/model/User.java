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
    @OneToMany
    @JoinColumn(referencedColumnName = "userId")
    private List<Task> tasks = new ArrayList<>();
    @OneToMany
    @JoinColumn(referencedColumnName = "userId")
    private List<Subject> subjects = new ArrayList<>();

}
