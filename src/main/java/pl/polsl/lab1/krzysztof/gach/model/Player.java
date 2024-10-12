package pl.polsl.lab1.krzysztof.gach.model;

import java.util.Scanner;

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
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidNameException("Name cannot be null or empty.");
        }
        
        if (name.length() < 3) {
            throw new InvalidNameException("Name must be at least 3 characters long.");
        }
        
        // Add more validation rules as necessary, e.g., no special characters
        if (!name.matches("[A-Z]+[a-z]*")) {
            throw new InvalidNameException("Name can only contain alphabetic characters.");
        }
    }
}
