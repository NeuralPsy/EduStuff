package il.neuralpsy.edustuff.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")})
public class User implements UserDetails {
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



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userType.getName());
        return Collections.singleton(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
