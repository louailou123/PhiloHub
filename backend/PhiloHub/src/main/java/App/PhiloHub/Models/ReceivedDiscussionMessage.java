package App.PhiloHub.Models;

import App.PhiloHub.Models.Enum.MessageStatus;
import App.PhiloHub.Models.JoinTablesKeys.ReceivedDiscussionMessageKey;
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
import java.time.LocalDate;
// imported below
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "received_discussion_messages")
@RequiredArgsConstructor
@Getter
@Setter
public class ReceivedDiscussionMessage {

    @EmbeddedId
    private ReceivedDiscussionMessageKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idDiscussionMessage")
    @JoinColumn(name = "id_discussion_message")
    private DiscussionMessage discussionMessage;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idReceiver")
    @JoinColumn(name = "id_receiver")
    private User receiver;
    
    @Column(name = "seen_at")
    private LocalDate seenAt;
    
    @Enumerated(EnumType.STRING)
    @jakarta.validation.constraints.NotNull
    @Column(name = "status")
    private MessageStatus status;
    
    @Column(name = "is_enabled")
    private Boolean isEnabled=true;
    
}
