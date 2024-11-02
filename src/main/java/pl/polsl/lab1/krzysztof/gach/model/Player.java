package pl.polsl.lab1.krzysztof.gach.model;

/**
 * The Player class represents a player in the game.
 * Each player has a name and a score that tracks their performance.
 * 
 * @author Krzysztof Gach
 * @version 1.1
 */
public class Player {   
    private String name; // The name of the player.
    private int score; // The player's score.
    private final String symbol;
    private final int id; 
    
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
     * Retrieves the current score of the player.
     *
     * @return the player's score
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Retrieves the unique identifier of the player.
     * 
     * @return the player's ID
     */
    public int getId(){
        return id;
    }
    
    /**
     * Retrieves the symbol associated with the player.
     * 
     * @return the player's symbol as a String
     */
    public String getSymbol() {
        return symbol;
    }
    
    /**
     * Updates the player's score with the specified value.
     * 
     * @param score the new score to set for the player
     */
    public void setScore(int score){
        this.score = score;
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
