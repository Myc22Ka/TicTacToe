package pl.polsl.lab1.krzysztof.gach.controller;

import javax.swing.JFrame;
import pl.polsl.lab1.krzysztof.gach.model.InvalidNameException;
import pl.polsl.lab1.krzysztof.gach.model.Player;
import pl.polsl.lab1.krzysztof.gach.view.MessageBox;

/**
 *
 * @author Krzysztof Gach
 */

public class Validator {
    private final Game game = Game.getInstance();
    
    public enum ValidationStatus {
        VALID,
        INVALID_SIZE,
        INVALID_SYMBOL,
        INVALID_NAME
    }
    
    public Validator(){}
    
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
    
    public ValidationStatus getGameStatus(JFrame frame){
         var messageBox = new MessageBox(frame); 
        
        if(game.getBoard().size() == 0){
            var input = messageBox.showInputDialog("Set board size: ", "Missing board size");
            
            if(input == null) return ValidationStatus.INVALID_SIZE;
            
            setValidSize(input, messageBox); 
        }
        
        if(game.getPlayers().isEmpty()){
            String[] player1Data = messageBox.showTwoInputDialog("Enter player1 name:", "Enter player1 symbol", "Player Names");
            
            if(player1Data == null) return ValidationStatus.INVALID_NAME;
            
            setValidPlayer(player1Data[0], player1Data[1], messageBox);
            
            String[] player2Data = messageBox.showTwoInputDialog("Enter player2 name:", "Enter player2 symbol", "Player Names");
            
            if(player2Data == null) return ValidationStatus.INVALID_NAME;
            
            setValidPlayer(player2Data[0], player2Data[1], messageBox);
        }
        
        if(game.getPlayers().size() == 1) {
            String[] player2Data = messageBox.showTwoInputDialog("Enter player2 name:", "Enter player2 symbol", "Player Names");
            
            if(player2Data == null) return ValidationStatus.INVALID_NAME;
            
            setValidPlayer(player2Data[0], player2Data[1], messageBox);
        }
        
        return ValidationStatus.VALID;
    }
}
