package App.PhiloHub.Repositories;

import App.PhiloHub.Models.ReceivedDiscussionMessage;
import App.PhiloHub.Models.JoinTablesKeys.ReceivedDiscussionMessageKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivedDiscussionMessageRepository extends JpaRepository<ReceivedDiscussionMessage, ReceivedDiscussionMessageKey> {
}
