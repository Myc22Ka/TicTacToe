package pl.polsl.lab1.krzysztof.gach.controller;

import pl.polsl.lab1.krzysztof.gach.view.Menu;

/**
 * The Controller class is responsible for managing the game flow and handling user input
 * for the console-based game. It interacts with the Game class to start the game,
 * process user actions, and update the game state.
 * 
 * @author Krzysztof Gach
 * @version 1.2
 */
public class Controller{  
    /**
     * The main method serves as the entry point for the application.
     * It initializes the Game and Controller, starts the game, and manages
     * the game loop.
     *
     * @param args command-line arguments passed to the program
     * Expected parameters:
     *  -s: size of the game
     *  -p1: name of the first player
     *  -p2: name of the second player
     * 
     */
    public static void main(String[] args){      
        Game game = Game.getInstance();
        Menu menu = new Menu();
        menu.displayWindow();
        
        game.startGame(args);
    }
}
