package App.PhiloHub.Repositories;

import App.PhiloHub.Models.User;
import App.PhiloHub.Models.Enum.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    Optional<User> findByAuthProviderAndProviderId(AuthProvider authProvider, String providerId);
}
