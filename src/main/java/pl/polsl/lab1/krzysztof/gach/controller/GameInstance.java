package pl.polsl.lab1.krzysztof.gach.controller;

import pl.polsl.lab1.krzysztof.gach.model.Board;
import pl.polsl.lab1.krzysztof.gach.model.Player;

/**
 *
 * @author kris
 */
public interface GameInstance {        
    void startGame();
    void endGame();
    void restartGame();
    Player getCurrentPlayer();
    Board getBoard();
    void nextTurn();
}
