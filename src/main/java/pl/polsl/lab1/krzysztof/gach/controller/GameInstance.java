package pl.polsl.lab1.krzysztof.gach.controller;

import pl.polsl.lab1.krzysztof.gach.model.Board;
import pl.polsl.lab1.krzysztof.gach.model.Player;

/**
 * The GameInstance interface defines the contract for game instances, specifying the 
 * essential operations required to manage a game.
 * 
 * @author Krzysztof Gach
 * @version 1.0
 */
public interface GameInstance {        
    
    /**
     * Starts the game with the given command-line arguments.
     *
     * @param args command-line arguments to configure the game
     */
    void startGame(String[] args);
    
    /**
     * Ends the current game and performs any necessary cleanup.
     */
    void endGame();
    
    /**
     * Restarts the game, resetting any game state as necessary.
     */
    void restartGame();
    
    /**
     * Retrieves the current player in the game.
     *
     * @return the current Player
     */
    Player getCurrentPlayer();
    
    /**
     * Retrieves the game board.
     *
     * @return the Board instance representing the game board
     */
    Board getBoard();
    
    /**
     * Advances to the next player's turn.
     */
    void nextTurn();
}
