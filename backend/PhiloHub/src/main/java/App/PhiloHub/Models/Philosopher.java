package App.PhiloHub.Models;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jdk.dynalink.linker.LinkerServices;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "philosophers")
@RequiredArgsConstructor
@Getter
@Setter
public class Philosopher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(unique = true)
    @NotBlank(message = "the name of the philosopher must not be null")
    @Size(max = 100 , message = "too long name for a philosopher")
    private String fullName;

    @ManyToMany(mappedBy = "philosophers")
    private List<Discussion> discussions;


}
