package polsl.krzysztof.gach.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private String gameId;
    private GameStatus status;
    private List<Player> playersList;
    private Board board;
    private TicToe winner;

    // No-args constructor
    public Game() {
        this.playersList = new ArrayList<Player>();
    }

    // All-args constructor
    public Game(String gameId, GameStatus status, List<Player> playersList, Board board, TicToe winner) {
        this.gameId = gameId;
        this.status = status;
        this.playersList = playersList;
        this.board = board;
        this.winner = winner;
    }

    // Getter for gameId
    public String getGameId() {
        return gameId;
    }

    // Setter for gameId
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    // Getter for status
    public GameStatus getStatus() {
        return status;
    }

    // Setter for status
    public void setStatus(GameStatus status) {
        this.status = status;
    }

    // Getter for playersList
    public List<Player> getPlayersList() {
        return playersList;
    }

    // Setter for playersList
    public void setPlayersList(List<Player> playersList) {
        this.playersList = playersList;
    }

    // Getter for board
    public Board getBoard() {
        return board;
    }

    // Setter for board
    public void setBoard(Board board) {
        this.board = board;
    }

    // Getter for winner
    public TicToe getWinner() {
        return winner;
    }

    // Setter for winner
    public void setWinner(TicToe winner) {
        this.winner = winner;
    }
}
