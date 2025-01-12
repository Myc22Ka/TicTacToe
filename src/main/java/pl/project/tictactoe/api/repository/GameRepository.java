package pl.project.tictactoe.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.project.tictactoe.api.model.Game;
import pl.project.tictactoe.api.model.GameStatus;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, String> {
    Optional<Game> findByGameId(String gameId);
    List<Game> findAllByStatus(GameStatus status);
}
