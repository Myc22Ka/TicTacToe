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
 * @version 1.2
 */
public class Game implements GameInstance{
    private static Game instance;
    
    private final Board board;
    private final PlayersList players;
    private GameState gameState;
    
    /**
     * Constructs a new Game instance with a default board and players list.
     */
    public Game() {
        this.board = new Board();
        this.players = new PlayersList();
    }
    
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    @Override
    public void startGame(String[] args) {
        ArgumentParser parser = new ArgumentParser();
        
        List<String> params = parser.parseArguments(String.join(" ",args));
        
        for(int i = 0; i < params.size(); i++){
            String playerSymbol = "";
            String[] splittedParams = params.get(i).split(" ");
            
            if(params.get(i).startsWith("-s")){
                board.setSize(splittedParams[1]);
            }
            
            if(params.get(i).startsWith("-p1")) playerSymbol = "X";
            
            else if(params.get(i).startsWith("-p2")) playerSymbol = "O";
            
            if(params.get(i).startsWith("-p1") || params.get(i).startsWith("-p2")){ 
                
                
                Player player = new Player(splittedParams[1], playerSymbol);
                
                boolean nameValid = false;
        
                while(!nameValid){
                    try {            
                        player.checkName(); // This will throw InvalidNameException if the name is invalid
                        players.add(player);
            
                        nameValid = true; // Exit the loop if the name is valid
            
                    } catch(InvalidNameException e){
                        System.out.print("You provided an incorrect name. Please enter the correct name: ");
            
                        Scanner scanner = new Scanner(System.in);
                        player = new Player(scanner.nextLine(), playerSymbol);
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
                
                player = new Player(scanner.nextLine(), "X");
            }
        }
    }
    
    @Override
    public void initPlayers(){
        if(players.getPlayersSizeList() == 2) return;
        
        System.out.print("Enter player1 name: ");
        
        Scanner scanner = new Scanner(System.in);
        this.addPlayer(new Player(scanner.nextLine(), "X"));
        
        System.out.print("Enter player2 name: ");
        
        this.addPlayer(new Player(scanner.nextLine() , "O"));
    }
    
    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public GameState getGameState() {
        return this.gameState;
    }
}
