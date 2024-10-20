package pl.polsl.lab1.krzysztof.gach.controller;

import java.util.List;
import java.util.Scanner;
import pl.polsl.lab1.krzysztof.gach.model.Board;
import pl.polsl.lab1.krzysztof.gach.model.InvalidNameException;
import pl.polsl.lab1.krzysztof.gach.model.Player;
import pl.polsl.lab1.krzysztof.gach.model.PlayersList;

/**
 * The Game class implements the GameInstance interface and manages the game logic,
 * including initializing the game board and players, starting the game, and handling
 * player turns.
 * 
 * @author Krzysztof Gach
 * @version 1.0
 */
public class Game implements GameInstance{
    private final Board board; // Instance of the Board class, responsible for managing the game board, including its layout and state.
    private final PlayersList players; // Instance of the PlayersList class, which keeps track of the players in the game, their statistics, and interactions.

    
    /**
     * Constructs a new Game instance with a default board and players list.
     */
    public Game() {
        this.board = new Board();
        this.players = new PlayersList();
    }

    @Override
    public void startGame(String[] args) {
        ArgumentParser parser = new ArgumentParser();
        
        List<String> params = parser.parseArguments(String.join(" ",args));
        
        for(int i = 0; i < params.size(); i++){
            String[] splittedParams = params.get(i).split(" ");
            
            if(params.get(i).startsWith("-s")){
                board.setSize(splittedParams[1]);
            }
            
            if(params.get(i).startsWith("-p1") || params.get(i).startsWith("-p2")){ 
                Player player = new Player(splittedParams[1]);
                
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
        }
        
        players.printPlayersNames();
        
        initPlayers();
    }

    @Override
    public void endGame() {
        System.out.println("Game Over");
    }

    @Override
    public void restartGame() {
        // Here I will implement restart logic here...
    }

    @Override
    public Player getCurrentPlayer() {
        return players.getCurrentPlayer();
    }

    @Override
    public void nextTurn() {
        players.nextPlayer();
    }
    
    @Override
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
    
    @Override
    public void initPlayers(){
        if(players.getPlayersSizeList() == 2) return;
        
        System.out.print("Enter player1 name: ");
        
        Scanner scanner = new Scanner(System.in);
        this.addPlayer(new Player(scanner.nextLine()));
        
        System.out.print("Enter player2 name: ");
        
        this.addPlayer(new Player(scanner.nextLine()));
    }
    
    @Override
    public Board getBoard() {
        return board;
    }
}
