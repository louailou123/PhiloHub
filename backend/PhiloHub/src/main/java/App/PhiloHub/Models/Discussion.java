package App.PhiloHub.Models;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "discussions")
@RequiredArgsConstructor
@Getter
@Setter
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "discussions_philosophers",
            joinColumns = @JoinColumn(name = "id_discussion") ,
            inverseJoinColumns = @JoinColumn(name = "id_philosopher")
    )
    private List<Philosopher> philosophers=new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "discussions_tags",
            joinColumns = @JoinColumn(name = "id_discussion"),
            inverseJoinColumns = @JoinColumn(name = "id_philosophy_tag")
    )
    private List<PhilosophyTag> philosophyTags ;
}
