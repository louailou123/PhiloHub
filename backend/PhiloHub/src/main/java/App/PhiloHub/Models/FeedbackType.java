package App.PhiloHub.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "feedback_type")
@RequiredArgsConstructor
@Getter
@Setter
public class FeedbackType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_feedback_type")
    private long id;

    @Column(name = "feedback_type_name")
    @Size(max=50 , message="feedback type name is too long")
    @NotNull(message="feedback type name is required")
    private String feedbackTypeName;

    @Column(name = "feedback_type_description")
    @Size(max=200 , message="feedback type description is too long")
    @NotNull(message="feedback type description is required")
    private String feedbackTypeDescription;

    @OneToMany(mappedBy = "feedbacksType", fetch = FetchType.LAZY)
    private List<Feedback> feedbacks = new ArrayList<>();

    @Column(name = "is_enabled")
    private Boolean isEnabled=true;
}
