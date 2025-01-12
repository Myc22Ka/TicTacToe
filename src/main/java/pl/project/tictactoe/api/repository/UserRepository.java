package pl.project.tictactoe.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.tictactoe.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
