package pl.polsl.lab1.krzysztof.gach.model;

import java.util.Scanner;
import pl.polsl.lab1.krzysztof.gach.controller.InvalidNameException;

/**
 *
 * @author Krzysztof Gach
 */

public class Player {   
    private String name;
    private int score;
    
    public Player(String name){
        this.name = name;
        this.score = 0;
    }
    
    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }
    
    public String getName(){
        return name;
    }
   
    public void setName(String name){       
        this.name = name;
    };
    
    public void scanName(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter player's name: ");
        
        this.setName(scanner.next());
    }
   
    public void checkName() throws InvalidNameException{
        // Check if the name is null, empty, or too short
        if (name == null || name.isEmpty() || name.length() < 3) {
            throw new InvalidNameException("Invalid name: Name must be at least 3 characters long and not empty.");
        }
    }
}
