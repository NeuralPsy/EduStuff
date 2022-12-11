package il.neuralpsy.edustuff.dto;

import il.neuralpsy.edustuff.model.Subject;
import il.neuralpsy.edustuff.model.Task;
import il.neuralpsy.edustuff.model.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.aspectj.lang.annotation.Before;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class UserDto {
    private Integer userId;
    private String name;
    private String email;
    private UserType userType;
    private LocalDate birthdate;

}
