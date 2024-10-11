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
    private Game game;
    private Scanner scanner;

    public Controller(Game game) {
        this.game = game;
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args){       
        Game game = new Game();
        Controller controller = new Controller(game);
        
        controller.manageArgs(args);
        
        game.startGame();
        
        int round = 0;
        
        // Simulate a game loop or handling user input
        while(!game.getBoard().isBoardFull(round)) {  // Example loop for handling 5 turns
            System.out.println("Player " + ((round % 2) + 1) + " turn");
            controller.handleUserInput(round % 2 == 0 ? "X" : "O");
            controller.updateGameState();
            
            round++;
        }

        game.endGame();
    }
    
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public void manageArgs(String[] args){
        if (args.length == 0) return;  // No arguments, return immediately
        
        // Loop through all arguments
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            // Check if there is a number after the current argument
            if (i + 1 < args.length && isNumeric(args[i + 1])) {
                int parsedArg = Integer.parseInt(args[i + 1]);
                
                // Handle the "-s" argument for board size
                if ("-s".equals(arg)) {
                    game.getBoard().resize(parsedArg, parsedArg);
                    System.out.println("New Size is set to: " + game.getBoard().getBoard().length);
                    i++; // Skip the next argument since it's already processed
                }
            } 
            
            // Handle cases where a number doesn't follow the argument
            else if ("-s".equals(arg)) {
                System.out.print("Set new Size to: ");
                int input = scanner.nextInt();  // Ask user for input if no number follows
                game.getBoard().resize(input, input);
                System.out.println("New Size is set to: " + game.getBoard().getBoard().length);
            } 
        }
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
