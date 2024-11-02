package pl.polsl.lab1.krzysztof.gach.controller;

/**
 * Represents the various states of the game.
 * 
 * @author Krzysztof Gach
 * @version 1.1
 */
public enum GameState {
    /** Main menu screen. */
    MENU, 
    
    /** Game is in progress. */
    PLAY, 
    
    /** Options menu screen. */
    MENU_OPTIONS, 
    
    /** Leaderboard screen displaying player scores. */
    MENU_LEADERBOARD, 
    
    /** Exits the game. */
    EXIT;
}
