package pl.polsl.lab1.krzysztof.gach.controller;
import pl.polsl.lab1.krzysztof.gach.model.Player;
import pl.polsl.lab1.krzysztof.gach.view.View;

/**
 * Class checks parameters for the console output.
 * @author Krzysztof Gach
 */
public class Controller {
    public static void main(String args[]){        
        Player[] players = new Player[2];
        
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player("");  // Create new Player and store in the array
            players[i].scanName();  // Ask for player's name
        }
        
//        if(args.length >= 4){
//            for (int i = 0; i < args.length; i++) {
//                if(args[i].equals("-p1")) players[0] = new Player(args[i + 1]);
//            
//                if(args[i].equals("-p2")) players[1] = new Player(args[i + 1]);
//            }
//        } else {
//        }

        for (Player player : players) {
            try {
                // Check if the player's name is valid
                player.checkName();
                // If valid, print the player's name
                System.out.println("Player: " + player.getName());
            } catch (InvalidNameException e) {
                // Handle invalid names
                System.out.println(e.getMessage());
            }
        }
                 
        System.out.println("Runda 1.");
        System.out.println(players[0].getName() + " vs " + players[1].getName());   
        
        View.printBoard();
    }
}
