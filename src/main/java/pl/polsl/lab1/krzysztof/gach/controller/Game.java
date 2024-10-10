package pl.polsl.lab1.krzysztof.gach.controller;

import pl.polsl.lab1.krzysztof.gach.model.Board;
import pl.polsl.lab1.krzysztof.gach.model.Cell;
import pl.polsl.lab1.krzysztof.gach.model.Player;
import pl.polsl.lab1.krzysztof.gach.model.PlayersList;

/**
 *
 * @author kris
 */
public class Game implements GameInstance{
    private Board board;
    private PlayersList players;
    private Controller controller;
//    private String<"paused"> gameState;
    
    public Game() {
        this.board = new Board();
        this.players = new PlayersList();
        this.controller = new Controller(this);
    }

    @Override
    public void startGame() {
        // ...
    }

    @Override
    public void endGame() {
        // ...
    }

    @Override
    public void restartGame() {
        // ...
    }

    @Override
    public Player getCurrentPlayer() {
        return players.getCurrentPlayer();
    }

    @Override
    public void nextTurn() {
        // ...
    }
    
    @Override
    public Board getBoard() {
        return board;
    }
}
