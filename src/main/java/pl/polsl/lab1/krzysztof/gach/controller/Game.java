package pl.polsl.lab1.krzysztof.gach.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
 * @version 1.1
 */

public class Game implements GameInstance {
    private static Game instance;
    
    private final Board board;
    private final PlayersList players;
    private GameState gameState;
    private int round;
    private List<String> args;
    private boolean isFullScreen = false;
    
    /**
     * Constructs a new Game instance with a default board and players list.
     */
    public Game() {
        this.board = new Board();
        this.players = new PlayersList();
        this.round = 1;
    }
    
    /**
     * Returns the singleton instance of the Game class, creating it if necessary.
     * 
     * @return the single instance of Game
     */
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    @Override
    public void setIsFullScreen(boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
    }
    
    @Override
    public boolean getIsFullScreen() {
        return this.isFullScreen;
    }
    
    @Override
    public void setArgs(String[] args) {
        this.args = new ArrayList<>(Arrays.asList(args));
    }
    
    @Override
    public List<String> getArgs() {
        return this.args;
    }
    
    @Override
    public void nextRound() {
        setRound(round + 1);
        board.clear();
    }
    
    @Override
    public List<Player> getPlayers() {
        return players.getAllPlayers();
    }
    
    @Override
    public int getRound() {
        return round;
    }
    
    @Override
    public void setRound(int round) {
        this.round = round;
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
