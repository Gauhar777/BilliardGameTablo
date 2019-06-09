package kz.ivc.games.repo;

import kz.ivc.games.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
 User findByUsername(String username);
}
