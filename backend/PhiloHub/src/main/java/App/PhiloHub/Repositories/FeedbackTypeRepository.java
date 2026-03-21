package App.PhiloHub.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import App.PhiloHub.Models.FeedbackType;

public interface FeedbackTypeRepository extends JpaRepository<FeedbackType, Long> {
    
}
