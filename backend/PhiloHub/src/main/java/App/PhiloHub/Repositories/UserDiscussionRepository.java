package App.PhiloHub.Repositories;

import App.PhiloHub.Models.UserDiscussion;
import App.PhiloHub.Models.JoinTablesKeys.UserDiscussionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDiscussionRepository extends JpaRepository<UserDiscussion, UserDiscussionKey> {
}
