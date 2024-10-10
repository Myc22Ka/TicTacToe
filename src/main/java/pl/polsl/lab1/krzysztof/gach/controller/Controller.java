package pl.polsl.lab1.krzysztof.gach.controller;

import pl.polsl.lab1.krzysztof.gach.view.View;

// Zagadnienia do testu
// protected 


/**
 * Class checks parameters for the console output.
 * @author Krzysztof Gach
 */
public class Controller{  
    private Game game;

    public Controller(Game game) {
        this.game = game;
    }

    public void handleUserInput(String input) {
        // Process user input and update the game accordingly
    }

    public void updateGameState() {
        // Updates game state based on current player actions, moves, etc.
    }
    
    public static void main(String[] args){       
        // Initialize the game
        Game game = new Game();

        // Create a controller to manage the game
        Controller controller = new Controller(game);
        
        controller.manageArgs(args);
        
        // Example game flow (this could be replaced with actual input handling)
        game.startGame();
        
        // Simulate a game loop or handling user input
        for (int i = 0; i < 5; i++) {  // Example loop for handling 5 turns
            controller.handleUserInput("Example Input " + (i + 1));
            controller.updateGameState();
        }

        game.endGame();
        System.out.println("Game Over");
        
//        System.out.print(board);
        
//        for (Player player : players) {
//            try {
//                // Check if the player's name is valid
//                player.checkName();
//                // If valid, print the player's name
//                System.out.println("Player: " + player.getName());
//            } catch (InvalidNameException e) {
//                // Handle invalid names
//                System.out.println(e.getMessage());
//            }
//        }
//                 
//        System.out.println("Runda 1.");
//        System.out.println(players[0].getName() + " vs " + players[1].getName());   
        
        View.printBoard(game.getBoard());
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
        if (args.length == 0) return;
        
        for (int i = 0; i < args.length; i++){
            String arg = args[i]; 
            
            if("-s".equals(arg) && i + 1 < args.length && isNumeric(args[i + 1])){
                int size = Integer.parseInt(args[i + 1]);   
                
                game.getBoard().resize(size, size);
                
                System.out.println("New Size is set to: " + game.getBoard().getBoard().length);
                
            }
        }
    }
}
