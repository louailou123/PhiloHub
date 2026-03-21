package App.PhiloHub.Repositories;

import App.PhiloHub.Models.PhilosophyTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhilosophyTagRepository extends JpaRepository<PhilosophyTag, Long> {
}
