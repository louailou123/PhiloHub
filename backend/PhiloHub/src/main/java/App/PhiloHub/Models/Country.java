package App.PhiloHub.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Country {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_country")
    private long id;

    @NotNull(message = "Country name is required")
    @Size(max = 70,message = "Country name must be less than 70 characters")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Country code is required")
    @Size(max = 3,message = "Country code must be less than 3 characters")
    @Column(name = "code")
    private String code;

    @Column(name = "is_enabled")
    private Boolean isEnabled=true;

}
