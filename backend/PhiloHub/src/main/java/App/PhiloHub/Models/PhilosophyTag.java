package App.PhiloHub.Models;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "philosophy_tags")
@RequiredArgsConstructor
@Getter
@Setter
public class PhilosophyTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_philosophy_tag")
    private long id;

    @Column(unique = true,name = "name")
    @NotBlank
    @Size(max = 50)
    private String name;

    @ManyToMany(mappedBy = "philosophyTags")
    private List<Discussion> discussions;

    @Column(name = "is_enabled")
    private Boolean isEnabled=true;
}
