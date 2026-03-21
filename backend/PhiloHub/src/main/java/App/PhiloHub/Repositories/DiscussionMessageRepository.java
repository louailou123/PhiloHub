package App.PhiloHub.Repositories;

import App.PhiloHub.Models.DiscussionMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscussionMessageRepository extends JpaRepository<DiscussionMessage, Long> {
}
