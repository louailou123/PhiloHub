package App.PhiloHub.Models;

import java.time.LocalDate;

import App.PhiloHub.Models.Enum.DiscussionRole;
import App.PhiloHub.Models.JoinTablesKeys.UserDiscussionKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_discussions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDiscussion {

    @EmbeddedId
    private UserDiscussionKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idUser")
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idDiscussion")
    @JoinColumn(name = "id_discussion")
    private Discussion discussion;
    
    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;
     
    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private DiscussionRole role;
    
    @Column(name = "is_enabled")
    private Boolean isEnabled=true;
}
