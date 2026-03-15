package App.PhiloHub.Models;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "email is required")
    @Column(unique = true)
    @Email(message = "invalid email type")
    @Size(max = 80,message = "email is too long")
    private String email;

    @NotBlank(message = "username is required")
    @Column(unique = true)
    @Size(max = 50,message = "username is too long")
    private String username;

    @Size(max = 50)
    @NotBlank
    private String firstName;

    @Size(max = 50)
    @NotBlank
    private String lastName;

    @Size(min = 8, max = 70)
    @NotBlank(message = "username is required")
    private String password;

    @NotNull(message = "birthday is required")
    @Past(message = "birthdate must be in the past")
    private LocalDate bithdate;

    @NotNull
    @Column(name = "country")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_country")
    private Country country;




    private Boolean enabled=true;

}
