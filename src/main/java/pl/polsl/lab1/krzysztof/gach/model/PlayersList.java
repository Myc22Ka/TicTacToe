package pl.polsl.lab1.krzysztof.gach.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Krzysztof Gach
 */
public class PlayersList {
    private final List<Player> players;
    private int currentPlayerIndex;

    public PlayersList() {
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
    }

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

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
    
    public void printPlayersNames(){
        for(Player player : players){
            System.out.println(player.getName());
        }
    }
    
    public void initPlayers(){
        if(players.size() == 2) return;
        
        System.out.print("Enter player1 name: ");
        
        Scanner scanner = new Scanner(System.in);
        this.addPlayer(new Player(scanner.nextLine()));
        
        System.out.print("Enter player2 name: ");
        
        this.addPlayer(new Player(scanner.nextLine()));
    }
}
