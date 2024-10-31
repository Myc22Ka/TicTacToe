package pl.polsl.lab1.krzysztof.gach.controller;

import java.util.List;
import pl.polsl.lab1.krzysztof.gach.model.Board;
import pl.polsl.lab1.krzysztof.gach.model.Player;
import pl.polsl.lab1.krzysztof.gach.model.PlayersList;

/**
 * The Game class implements the GameInstance interface and manages the game logic,
 * including initializing the game board and players, starting the game, and handling
 * player turns.
 * 
 * @author Krzysztof Gach
 * @version 1.4
 */
public class Game implements GameInstance{
    private static Game instance;
    
    private final Board board;
    private final PlayersList players;
    private GameState gameState;
    private int round;
    private String[] args;
    
    /**
     * Constructs a new Game instance with a default board and players list.
     */
    public Game() {
        this.board = new Board();
        this.players = new PlayersList();
        this.round = 1;
    }
    
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
    
    public void setArgs(String[] args){
        this.args = args;
    }
    
    public String[] getArgs(){
        return this.args;
    }
    
    public void nextRound(){
        setRound(round + 1);
        board.clear();
    }
    
    public List<Player> getPlayers(){
        return players.getAllPlayers();
    }
    
    public int getRound(){
        return round;
    }
    
    public void setRound(int round){
        this.round = round;
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
        players.add(player);
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
