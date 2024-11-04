package pl.polsl.lab1.krzysztof.gach.controller;

import lombok.*;
import javax.swing.JFrame;
import pl.polsl.lab1.krzysztof.gach.model.InvalidNameException;
import pl.polsl.lab1.krzysztof.gach.model.Player;
import pl.polsl.lab1.krzysztof.gach.view.MessageBox;

/**
 * The Validator class provides validation methods for game settings,
 * including board size and player details. It ensures that these settings are valid
 * before proceeding with the game.
 * 
 * @author Krzysztof Gach
 * @version 1.0
 */

@NoArgsConstructor
public class Validator {
    private final Game game = Game.getInstance();
    
    /**
     * Enum representing validation status outcomes for the game setup.
     */
    public enum ValidationStatus {
        /** Indicates valid input. */
        VALID,

        /** Indicates an invalid board size. */
        INVALID_SIZE,

        /** Indicates an invalid player symbol. */
        INVALID_SYMBOL,

        /** Indicates an invalid player name. */
        INVALID_NAME
    }
    
    /**
     * Validates and sets the board size for the game.
     * 
     * Prompts the user for a valid board size if the input is invalid.
     * 
     * @param sizeInput   the initial board size input as a string
     * @param messageBox  the MessageBox used to display input dialogs for validation
     */
    public void setValidSize(String sizeInput, MessageBox messageBox) {
        Integer size = null;
        while (size == null) {
            try {
                size = Integer.valueOf(sizeInput);
                
                game.getBoard().setSize(size);
            } catch (NumberFormatException e) {
                sizeInput = messageBox.showInputDialog("Invalid size. Please enter a valid integer:", "Invalid Board Size");
            }
        }
    }
    
    /**
     * Validates and adds a player to the game.
     * 
     * Ensures the player symbol is a single character and the name is valid.
     * If invalid, prompts the user to re-enter until valid.
     * 
     * @param name        the name of the player
     * @param symbol      the symbol of the player
     * @param messageBox  the MessageBox used to display input dialogs for validation
     */
    public void setValidPlayer(String name, String symbol, MessageBox messageBox) {
        while (symbol == null || symbol.length() != 1) {
            symbol = messageBox.showInputDialog("Invalid symbol. Please enter a single character symbol:", "Invalid Symbol");
        }
        
        Player player = new Player(name, symbol, game.getPlayers().size());
        boolean nameValid = false;

        while (!nameValid) {
            try {
                player.checkName();
                nameValid = true;
            } catch (InvalidNameException e) {
                name = messageBox.showInputDialog(e.getMessage(), "Invalid Name");
                player = new Player(name, symbol, game.getPlayers().size());
            }
        }
    
        game.addPlayer(player);
    }
    
    /**
     * Checks the game setup status, ensuring that required parameters (board size and players) are valid.
     * 
     * If any parameters are missing or invalid, prompts the user to enter them.
     * 
     * @param frame the JFrame that serves as the parent frame for the MessageBox dialogs
     * @return a ValidationStatus indicating if the setup is valid or if there are missing/invalid settings
     */
    public ValidationStatus getGameStatus(JFrame frame){
         var messageBox = new MessageBox(frame); 
        
        if(game.getBoard().size() == 0){
            var input = messageBox.showInputDialog("Set board size: ", "Missing board size");
            
            if(input == null) return ValidationStatus.INVALID_SIZE;
            
            setValidSize(input, messageBox); 
        }
        
        if(game.getPlayers().isEmpty()){
            var player1Data = messageBox.showTwoInputDialog("Enter player1 name:", "Enter player1 symbol", "Player Names");
            
            if(player1Data == null) return ValidationStatus.INVALID_NAME;
            
            setValidPlayer(player1Data.get(0), player1Data.get(1), messageBox);
            
            var player2Data = messageBox.showTwoInputDialog("Enter player2 name:", "Enter player2 symbol", "Player Names");
            
            if(player2Data == null) return ValidationStatus.INVALID_NAME;
            
            setValidPlayer(player2Data.get(0), player2Data.get(1), messageBox);
        }
        
        if(game.getPlayers().size() == 1) {
            var player2Data = messageBox.showTwoInputDialog("Enter player2 name:", "Enter player2 symbol", "Player Names");
            
            if(player2Data == null) return ValidationStatus.INVALID_NAME;
            
            setValidPlayer(player2Data.get(0), player2Data.get(1), messageBox);
        }
        
        return ValidationStatus.VALID;
    }
}
