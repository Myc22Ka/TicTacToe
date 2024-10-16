package pl.polsl.lab1.krzysztof.gach.controller;

import java.util.Scanner;
import pl.polsl.lab1.krzysztof.gach.view.View;

// Zagadnienia do testu
// protected 

/**
 * The Controller class is responsible for managing the game flow and handling user input
 * for the console-based game. It interacts with the Game class to start the game,
 * process user actions, and update the game state.
 * 
 * 
 * @author Krzysztof Gach
 * @version 1.0
 */
public class Controller{  
    private final Game game;
    private final Scanner scanner;

    /**
     * Constructs a new Controller instance with the specified Game.
     *
     * @param game the Game instance to be managed by this controller
     */

    private Controller(Game game) {
        this.game = game;
        this.scanner = new Scanner(System.in);
    }

    /**
     * The main method serves as the entry point for the application.
     * It initializes the Game and Controller, starts the game, and manages
     * the game loop.
     *
     * @param args command-line arguments passed to the program
     */
    public static void main(String[] args){       
        Game game = new Game();
        Controller controller = new Controller(game);
        
        game.startGame(args);
        
        int round = 0;
        
        // Simulate a game loop or handling user input
        while(!game.getBoard().isBoardFull(round)) {  // Example loop for handling 5 turns
            System.out.println(game.getCurrentPlayer().getName() + " turn...");
            controller.handleUserInput(round % 2 == 0 ? "X" : "O");
            controller.updateGameState();
            
            round++;
            game.nextTurn();
        }

        game.endGame();
    }
    
    /**
     * Handles user input for placing a mark on the game board.
     * It prompts the user for the X and Y coordinates and updates the board.
     *
     * @param input the mark to place on the board (either "X" or "O")
     */
    private void handleUserInput(String input) {   
        int x,y;
        
        System.out.print("Set X pos: ");
        x = scanner.nextInt();
        
        System.out.print("Set Y pos: ");
        y = scanner.nextInt();
        
        game.getBoard().updateBoard(x, y, input);
    }

    /**
     * Updates the current state of the game and prints the board.
     */
    private void updateGameState() {
        View.printBoard(game.getBoard());
    }
    
}
