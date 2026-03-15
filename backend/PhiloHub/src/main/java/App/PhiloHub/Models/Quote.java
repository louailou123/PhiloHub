package App.PhiloHub.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.*;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;

@Entity
@Table(name = "quotes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Quote must not be blank")

    @Size(max = 100, message = "Quote must be at most 100 characters")
    @Column(unique = true)
    private String text;
    @NotBlank(message = "Philosopher must not be null")
    @Size(max = 100, message ="too long name for a philosopher")

    private String philosopher;
    @NotBlank(message = "era must not be null")
    private String era;

    private Boolean enabled=true;
}
