package App.PhiloHub.Repositories;

import App.PhiloHub.Models.Philosopher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhilosopherRepository extends JpaRepository<Philosopher,Long> {
}
