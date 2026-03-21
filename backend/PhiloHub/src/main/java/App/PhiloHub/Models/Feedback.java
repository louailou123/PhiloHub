package App.PhiloHub.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "feedback")
@RequiredArgsConstructor
@Getter
@Setter
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_feedback")
    private long id;

    @Column(name = "feedback_message")
    private String feedbackMessage;

    @Column(name = "feedback_created_at")
    private LocalDate feedbackCreatedAt;
    
    @Column(name = "is_read")
    private boolean isRead=false;
      
    @Column(name = "read_at")
    private LocalDate readAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_feedback_type")
    private FeedbackType feedbacksType;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "is_enabled")
    private Boolean isEnabled=true;
}
