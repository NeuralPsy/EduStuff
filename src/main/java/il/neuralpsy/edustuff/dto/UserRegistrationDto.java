package il.neuralpsy.edustuff.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegistrationDto {
    @NotEmpty(message = "This field should not be empty")
    private String name;
    private String birthdate;
    @Email(message = "Enter correct email format. E.g. user@post.com")
    private String email;
    @NotEmpty(message = "This field should not be empty")
    @Size(min = 5, max = 15, message = "Password should contain from 5 to 15 characters")
    private String password;
    @NotEmpty(message = "User role should be chosen")
    private String userType;
}
