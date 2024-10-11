package pl.polsl.lab1.krzysztof.gach.controller;

import java.util.Arrays;
import java.util.List;
import pl.polsl.lab1.krzysztof.gach.model.Board;
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
    public void startGame(String[] args) {
        ArgumentParser parser = new ArgumentParser();
        
        List<String> params = parser.parseArguments(String.join(" ",args));
        
        for(int i = 0; i < params.size(); i++){
            System.out.println(params.get(i));
        }
    }
    

    @Override
    public void endGame() {
        System.out.println("Game Over");
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
