package App.PhiloHub.Models;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "philosophy_tags")
public class PhilosophyTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @NotBlank
    @Size(max = 50)
    private String name;

    @ManyToMany(mappedBy = "philosophyTags")
    private List<Discussion> discussions;
}
