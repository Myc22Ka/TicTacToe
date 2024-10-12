package pl.polsl.lab1.krzysztof.gach.controller;

import java.util.Scanner;
import pl.polsl.lab1.krzysztof.gach.view.View;

// Zagadnienia do testu
// protected 

/**
 * Class checks parameters for the console output.
 * @author Krzysztof Gach
 */
public class Controller{  
    private final Game game;
    private final Scanner scanner;

    public Controller(Game game) {
        this.game = game;
        this.scanner = new Scanner(System.in);
    }

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
    
    public void handleUserInput(String input) {   
        int x,y;
        
        System.out.print("Set X pos: ");
        x = scanner.nextInt();
        
        System.out.print("Set Y pos: ");
        y = scanner.nextInt();
        
        game.getBoard().updateBoard(x, y, input);
    }

    public void updateGameState() {
        View.printBoard(game.getBoard());
    }
    
}
