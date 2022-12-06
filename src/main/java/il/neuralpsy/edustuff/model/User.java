package il.neuralpsy.edustuff.model;

import il.neuralpsy.edustuff.event.FeedEvent;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")})
public class User {
    @Id
    @GeneratedValue
    private Integer userId;
    private String name;
    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;
    private String password;
    private Boolean locked;
    private Boolean enabled;
    @OneToOne
    @JoinColumn(referencedColumnName = "userTypeId")
    private UserType userType;
    private LocalDate birthdate;
    @OneToMany(mappedBy = "user")
    private Set<Task> tasks;
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Subject> subjects;
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Comment> comments;
    @OneToMany(mappedBy = "user")
    private Set<FeedEvent> events;



}
