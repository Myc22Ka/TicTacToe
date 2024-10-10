package pl.polsl.lab1.krzysztof.gach.controller;

import pl.polsl.lab1.krzysztof.gach.model.Board;
import pl.polsl.lab1.krzysztof.gach.model.PlayersList;
import pl.polsl.lab1.krzysztof.gach.view.View;

// Zagadnienia do testu
// protected 


/**
 * Class checks parameters for the console output.
 * @author Krzysztof Gach
 */
public class Controller{  
    private GameInstance gameInstance;
    private Board board;
    
    public Controller(){
        this.gameInstance = new Game();
        this.board = new Board();
    }
    
    public static void main(String[] args){       
        Controller controller = new Controller();
        
        controller.manageArgs(args);
        
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
        
        View.printBoard();
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
                
                board.setNewSize(size);
                
                
                System.out.println("New Size is set to: " + board.getSize());
                
            }
        }
    }
}
