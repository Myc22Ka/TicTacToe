package pl.polsl.lab1.krzysztof.gach.model;

import java.util.Scanner;
import pl.polsl.lab1.krzysztof.gach.controller.InvalidNameException;

/**
 *
 * @author Krzysztof Gach
 */

public class Player {   
    private String playerName;
    
    public Player(String name){
        this.playerName = name;
    }
    
    public String getName(){
        return playerName;
    }
   
    public void setName(String newName){       
        playerName = newName;
    };
    
    public void scanName(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter player's name: ");
        
        this.setName(scanner.next());
    }
   
    public void checkName() throws InvalidNameException{
        // Check if the name is null, empty, or too short
        if (playerName == null || playerName.isEmpty() || playerName.length() < 3) {
            throw new InvalidNameException("Invalid name: Name must be at least 3 characters long and not empty.");
        }
    }
}
