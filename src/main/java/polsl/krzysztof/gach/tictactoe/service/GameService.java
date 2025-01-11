package polsl.krzysztof.gach.tictactoe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import polsl.krzysztof.gach.tictactoe.exception.InvalidGameException;
import polsl.krzysztof.gach.tictactoe.exception.InvalidParamException;
import polsl.krzysztof.gach.tictactoe.exception.NotFoundException;
import polsl.krzysztof.gach.tictactoe.model.*;
import polsl.krzysztof.gach.tictactoe.storage.GameStorage;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GameService {

    public Game createGame(Player player){
        Game game = new Game();
        game.setBoard(new Board());
        game.setGameId(UUID.randomUUID().toString());
        game.getPlayersList().add(player);
        game.setStatus(GameStatus.NEW);
        GameStorage.getInstance().setGame(game);

        return game;
    }

    private void setupSecondPlayer(Player player, Game game){
        game.getPlayersList().add(player);
        game.setStatus(GameStatus.PLAYING);
        GameStorage.getInstance().setGame(game);
    }

    public Game connectToGame(Player player, String gameId) throws InvalidParamException {
        if(!GameStorage.getInstance().getGames().containsKey(gameId)){
            throw new InvalidParamException("Game with provided id doesn't exist");
        }

        var game = GameStorage.getInstance().getGames().get(gameId);

        if(game.getPlayersList().size() != 2){
            throw new InvalidGameException("Game is not valid anymore");
        }

        setupSecondPlayer(player, game);

        return game;
    }

    public Game connectToRandomGame(Player player){
        Game game = GameStorage.getInstance().getGames().values().stream()
                .filter(it -> it.getStatus().equals(GameStatus.NEW))
                .findFirst().orElseThrow(() -> new NotFoundException("Game not found"));

        setupSecondPlayer(player, game);

        return game;
    }

    public Game gamePlay(GamePlay gamePlay) throws NotFoundException, InvalidGameException{
        if(!GameStorage.getInstance().getGames().containsKey(gamePlay.getGameId())){
            throw new NotFoundException("Game not found");
        }

        Game game = GameStorage.getInstance().getGames().get(gamePlay.getGameId());

        if(game.getStatus().equals(GameStatus.FINISHED)){
            throw new InvalidGameException("Game is already finished");
        }

        Board board = game.getBoard();
        board.setCell(gamePlay.getCoordinates(), new Cell(gamePlay.getType().getValue()));

        checkWinner(game.getBoard(), TicToe.X);
        checkWinner(game.getBoard(), TicToe.O);

        GameStorage.getInstance().setGame(game);
        return game;
    }

    private void checkWinner(Board board, TicToe ticToe){
        TicToe winner = board.checkWin();

    }
}
