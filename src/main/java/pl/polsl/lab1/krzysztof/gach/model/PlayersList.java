package pl.polsl.lab1.krzysztof.gach.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The PlayersList class manages a list of players in the game.
 * It allows adding players, tracking the current player, and printing player names.
 * 
 * @author: Krzysztof Gach
 * @version 1.0
 */
public class PlayersList {
    private final List<Player> players;
    private int currentPlayerIndex;

    
    /**
     * Constructs a new PlayersList instance with an empty player list 
     * and sets the current player index to 0.
     */
    public PlayersList() {
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
    }

    /**
     * Adds a player to the list after validating their name.
     *
     * @param player the Player instance to be added
     */
    public void addPlayer(Player player) {
        boolean nameValid = false;
        
        while(!nameValid){
            try {            
                player.checkName(); // This will throw InvalidNameException if the name is invalid
                players.add(player);
            
                nameValid = true; // Exit the loop if the name is valid
            
            } catch(InvalidNameException e){
                System.out.print("You provided an incorrect name. Please enter the correct name: ");
            
                Scanner scanner = new Scanner(System.in);
                player = new Player(scanner.nextLine());
            }
        }
    }

    /**
     * Retrieves the current player from the list.
     *
     * @return the current Player instance
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /**
     * Advances to the next player in the list.
     * If the end of the list is reached, it wraps around to the first player.
     */
    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
    
    /**
     * Prints the names of all players in the list to the console.
     */
    public void printPlayersNames(){
        for(Player player : players){
            System.out.println(player.getName());
        }
    }
    
    /**
     * Initializes players by prompting for player names if there are not already 2 players in the list.
     */
    public void initPlayers(){
        if(players.size() == 2) return;
        
        System.out.print("Enter player1 name: ");
        
        Scanner scanner = new Scanner(System.in);
        this.addPlayer(new Player(scanner.nextLine()));
        
        System.out.print("Enter player2 name: ");
        
        this.addPlayer(new Player(scanner.nextLine()));
    }
}
