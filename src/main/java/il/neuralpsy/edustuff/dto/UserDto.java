package il.neuralpsy.edustuff.dto;

import il.neuralpsy.edustuff.model.Subject;
import il.neuralpsy.edustuff.model.Task;
import il.neuralpsy.edustuff.model.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

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
//    private List<TaskDto> tasks;
//    private List<Subject> subjects;

}
