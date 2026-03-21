package App.PhiloHub.Models;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "discussions")
@RequiredArgsConstructor
@Getter
@Setter
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_discussion")
    private long id;

    @Column(name = "discussion_title")
    private String title;

    @Column(name = "discussion_description")
    private String description;

    @Column(name = "discussion_created_at")
    private LocalDate createdAt;

    @Column(name = "discussion_updated_at")
    private LocalDate updatedAt;
     
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

    @OneToMany(mappedBy = "discussion", fetch = FetchType.LAZY)
    private List<UserDiscussion> userDiscussions = new ArrayList<>();

    @OneToMany(mappedBy = "discussion", fetch = FetchType.LAZY)
    private List<DiscussionMessage> sentDiscussionMessages = new ArrayList<>();
    
 

    @Column(name = "is_enabled")
    private Boolean isEnabled=true;

}
