package il.neuralpsy.edustuff.dto;

import il.neuralpsy.edustuff.model.UserType;
import lombok.*;

import java.time.LocalDate;

@Data
public class UserDto {
    private Integer userId;
    private String name;
    private String email;
    private UserType userType;
    private LocalDate birthdate;

}
