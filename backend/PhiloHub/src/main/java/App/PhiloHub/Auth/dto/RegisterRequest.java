package App.PhiloHub.Auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDate;

@Data
public class RegisterRequest {
    @NotBlank(message = "email is required")
    @Email(message = "invalid email format")
    private String email;

    @NotBlank(message = "username is required")
    @Size(max = 50, message = "username is too long")
    private String username;

    @NotBlank(message = "first name is required")
    @Size(max = 50)
    private String firstName;

    @NotBlank(message = "last name is required")
    @Size(max = 50)
    private String lastName;

    @NotBlank(message = "password is required")
    @Size(min = 8, max = 70)
    private String password;

    @NotNull(message = "birthday is required")
    @Past(message = "birthdate must be in the past")
    private LocalDate bithdate;

    @NotNull(message = "Country is required")
    private Long countryId;
}
