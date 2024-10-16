package pl.polsl.lab1.krzysztof.gach.controller;

import java.util.List;
import pl.polsl.lab1.krzysztof.gach.model.Board;
import pl.polsl.lab1.krzysztof.gach.model.Player;
import pl.polsl.lab1.krzysztof.gach.model.PlayersList;

/**
 * The Game class implements the GameInstance interface and manages the game logic,
 * including initializing the game board and players, starting the game, and handling
 * player turns.
 * 
 * @author Krzysztof Gach
 * @version 1.0
 */
public class Game implements GameInstance{
    private final Board board;
    private final PlayersList players;
    
    /**
     * Constructs a new Game instance with a default board and players list.
     */
    public Game() {
        this.board = new Board();
        this.players = new PlayersList();
    }

    @Override
    public void startGame(String[] args) {
        ArgumentParser parser = new ArgumentParser();
        
        List<String> params = parser.parseArguments(String.join(" ",args));
        
        for(int i = 0; i < params.size(); i++){
            String[] splittedParams = params.get(i).split(" ");
            
            if(params.get(i).startsWith("-s")){
                board.setSize(splittedParams[1]);
            }
            
            if(params.get(i).startsWith("-p1") || params.get(i).startsWith("-p2")){ 
                players.addPlayer(new Player(splittedParams[1]));
            }
        }
        
        players.printPlayersNames();
        
        players.initPlayers();
    }

    @Override
    public void endGame() {
        System.out.println("Game Over");
    }

    @Override
    public void restartGame() {
        // Here I will implement restart logic here...
    }

    @Override
    public Player getCurrentPlayer() {
        return players.getCurrentPlayer();
    }

    @Override
    public void nextTurn() {
        players.nextPlayer();
    }
    
    @Override
    public Board getBoard() {
        return board;
    }
}
