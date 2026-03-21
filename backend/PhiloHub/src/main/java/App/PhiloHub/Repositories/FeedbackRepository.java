package App.PhiloHub.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import App.PhiloHub.Models.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    
}
