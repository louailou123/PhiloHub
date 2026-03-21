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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "sent_discussion_messages")
@RequiredArgsConstructor
@Getter
@Setter
public class DiscussionMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sent_discussion_message")
    private long id;

    @Column(name = "message")
    private String message;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "is_enabled")
    private Boolean isEnabled=true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_discussion")
    private Discussion discussion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sender")
    private User sender;

    @OneToMany(mappedBy = "discussionMessage", fetch = FetchType.LAZY)
    private List<ReceivedDiscussionMessage> receivedDiscussionMessages = new ArrayList<>();
    
}
