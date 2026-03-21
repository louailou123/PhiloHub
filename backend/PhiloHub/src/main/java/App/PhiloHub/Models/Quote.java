package App.PhiloHub.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quotes")
@RequiredArgsConstructor
@Setter
@Getter

public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quote")
    private Long id;
    @NotBlank(message = "Quote must not be blank")

    @Size(max = 100, message = "Quote must be at most 100 characters")
    @Column(unique = true ,name = "text")
    private String text;
    @NotBlank(message = "Philosopher must not be null")
    @Size(max = 100, message ="too long name for a philosopher")
    @Column(name = "philosopher")
    private String philosopher;
    @NotBlank(message = "era must not be null")
    @Column(name = "era")
    private String era;

    @Column(name = "is_enabled")
    private Boolean enabled=true;
}
