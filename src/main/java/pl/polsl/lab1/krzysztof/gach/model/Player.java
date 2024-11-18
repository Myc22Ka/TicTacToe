package pl.polsl.lab1.krzysztof.gach.model;

import lombok.*;

/**
 * The Player class represents a player in the game.
 * Each player has a name and a score that tracks their performance.
 * 
 * @author Krzysztof Gach
 * @version 1.1
 */

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Player {   
    private String name; // The name of the player.
    private int score = 0; // The player's score.
    private final String symbol; // The symbol representing the player in the game
    private final int id; // The unique identifier for the player

    /**
     * Constructs a new Player instance with a specified name, symbol, and an initial score of 0.
     *
     * @param name the name of the player
     * @param symbol the symbol representing the player in the game
     * @param id the unique identifier for the player
     */
    public Player(String name, String symbol, int id){
        this.name = name;
        this.score = 0;
        this.symbol = symbol;
        this.id = id;
    }

    /**
     * Adds points to the player's score.
     *
     * @param points the number of points to add to the score
     */
    public void addScore(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Score cannot be negative.");
        }
        this.score += points;
    }
    
    /**
     * Checks if the player's name is valid according to specified criteria.
     * 
     * @throws InvalidNameException if the name is null, empty, less than 3 characters, 
     *         or contains invalid characters
     */
    public void checkName() throws InvalidNameException {
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