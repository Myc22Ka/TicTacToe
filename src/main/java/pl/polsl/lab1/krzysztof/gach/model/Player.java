package pl.polsl.lab1.krzysztof.gach.model;

import java.util.Scanner;

/**
 * The Player class represents a player in the game.
 * Each player has a name and a score that tracks their performance.
 * 
 * @author: Krzysztof Gach
 * @version 1.0
 */
public class Player {   
    private String name;
    private int score;
    
    /**
     * Constructs a new Player instance with a specified name and an initial score of 0.
     *
     * @param name the name of the player
     */
    public Player(String name){
        this.name = name;
        this.score = 0;
    }
    
    /**
     * Retrieves the current score of the player.
     *
     * @return the player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Adds points to the player's score.
     *
     * @param points the number of points to add to the score
     */
    public void addScore(int points) {
        this.score += points;
    }
    
    /**
     * Retrieves the name of the player.
     *
     * @return the player's name
     */
    public String getName(){
        return name;
    }
   
    /**
     * Sets a new name for the player.
     *
     * @param name the new name to be set for the player
     */
    public void setName(String name){       
        this.name = name;
    };
    
    /**
     * Prompts the user to enter the player's name and sets it.
     */
    public void scanName(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter player's name: ");
        
        this.setName(scanner.next());
    }
   
    /**
     * Checks if the player's name is valid according to specified criteria.
     * 
     * @throws InvalidNameException if the name is null, empty, less than 3 characters, 
     *         or contains invalid characters
     */
    public void checkName() throws InvalidNameException{
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidNameException("Name cannot be null or empty.");
        }
        
        if (name.length() < 3) {
            throw new InvalidNameException("Name must be at least 3 characters long.");
        }
        
        if (!name.matches("[A-Z]+[a-z]*")) {
            throw new InvalidNameException("Name can only contain alphabetic characters.");
        }
    }
}
