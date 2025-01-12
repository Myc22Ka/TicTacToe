package pl.project.tictactoe.api.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.project.tictactoe.api.model.*;
import pl.project.tictactoe.api.repository.GameRepository;
import pl.project.tictactoe.api.repository.UserRepository;
import pl.project.tictactoe.api.storage.GameStorage;
import pl.project.tictactoe.exception.AppException;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game createGame(String playerLogin) {
        User player = userRepository.findByLogin(playerLogin);
        if (player == null) {
            throw new RuntimeException("Player not found");
        }

        Game game = new Game();
        game.setBoard(new int[3][3]);
        game.setGameId(UUID.randomUUID().toString());
        game.setPlayer1(player);
        game.setStatus(GameStatus.NEW);
        game.setWinner(null);

        return gameRepository.save(game);
    }

    public Game connectToGame(User player2, String gameId) throws AppException {
        Game game = gameRepository.findByGameId(gameId)
                .orElseThrow(() -> new AppException("Game with provided id doesn't exist"));

        if (game.getPlayer2() != null) {
            throw new AppException("Game is not valid anymore");
        }

        game.setPlayer2(player2);
        game.setStatus(GameStatus.IN_PROGRESS);

        return gameRepository.save(game);
    }

    public Game connectToRandomGame(User player2) throws AppException {
        // Retrieve all games with NEW status
        List<Game> newGames = gameRepository.findAllByStatus(GameStatus.NEW);

        // Throw exception if no games are available
        if (newGames.isEmpty()) {
            throw new AppException("No available games found");
        }

        // Pick a random game from the list
        Random random = new Random();
        Game selectedGame = newGames.get(random.nextInt(newGames.size()));

        // Update the selected game
        selectedGame.setPlayer2(player2);
        selectedGame.setStatus(GameStatus.IN_PROGRESS);

        // Save and return the updated game
        return gameRepository.save(selectedGame);
    }

    public Game gamePlay(GamePlay gamePlay) throws AppException {
        // Find the game by gameId
        Game game = gameRepository.findByGameId(gamePlay.getGameId())
                .orElseThrow(() -> new AppException("Game not found"));

        // Check if the game is already finished
        if (game.getStatus().equals(GameStatus.FINISHED)) {
            throw new AppException("Game is already finished");
        }

        // Check if the player making the move is the current player
        User currentPlayer = game.getPlayer2();

        // Update the board with the new move
        int[][] board = game.getBoard();
        board[gamePlay.getCoordinateX()][gamePlay.getCoordinateY()] = gamePlay.getType().getValue();

        // Check if there's a winner
        Boolean xWinner = checkWinner(game.getBoard(), TicToe.X);
        Boolean oWinner = checkWinner(game.getBoard(), TicToe.O);

        // Update game status and winner
        if (xWinner) {
            game.setWinner(TicToe.X);
            game.setStatus(GameStatus.FINISHED);
        } else if (oWinner) {
            game.setWinner(TicToe.O);
            game.setStatus(GameStatus.FINISHED);
        }

        // Save the updated game state
        return gameRepository.save(game);
    }

    private Boolean checkWinner(int[][] board, TicToe ticToe) {
        int[] boardArray = new int[9];
        int counterIndex = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boardArray[counterIndex] = board[i][j];
                counterIndex++;
            }
        }

        int[][] winCombinations = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        for (int i = 0; i < winCombinations.length; i++) {
            int counter = 0;
            for (int j = 0; j < winCombinations[i].length; j++) {
                if (boardArray[winCombinations[i][j]] == ticToe.getValue()) {
                    counter++;
                    if (counter == 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
