package pl.project.tictactoe.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import pl.project.tictactoe.api.dto.ConnectRequest;
import pl.project.tictactoe.api.model.Game;
import pl.project.tictactoe.api.model.GamePlay;
import pl.project.tictactoe.api.model.TicToe;
import pl.project.tictactoe.api.model.User;
import pl.project.tictactoe.api.service.GameService;
import pl.project.tictactoe.exception.AppException;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/start")
    public ResponseEntity<Game> start(@RequestBody User player) {
        log.info("start game request: {}", player);
        return ResponseEntity.ok(gameService.createGame(player.getLogin()));
    }

    @PostMapping("/connect")
    public ResponseEntity<Game> connect(@RequestBody ConnectRequest request) throws AppException {
        log.info("connect request: {}", request);
        return ResponseEntity.ok(gameService.connectToGame(request.getPlayer(), request.getGameId()));
    }

    @PostMapping("/connect/random")
    public ResponseEntity<Game> connectRandom(@RequestBody User player) throws AppException {
        log.info("connect random {}", player);
        return ResponseEntity.ok(gameService.connectToRandomGame(player));
    }

    @PostMapping("/gameplay")
    public ResponseEntity<Game> gamePlay(@RequestBody GamePlay request) throws AppException {
        log.info("gameplay: {}", request);
        Game game = gameService.gamePlay(request);

        // Return the updated game state after the move
        return ResponseEntity.ok(game);
    }

    @GetMapping("/all")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

}
