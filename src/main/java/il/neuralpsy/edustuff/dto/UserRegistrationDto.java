package il.neuralpsy.edustuff.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegistrationDto {
    private String name;
    private String birthdate;
    private String email;
    private String password;
    private String userType;
}
