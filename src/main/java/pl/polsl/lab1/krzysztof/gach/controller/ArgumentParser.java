package pl.polsl.lab1.krzysztof.gach.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import pl.polsl.lab1.krzysztof.gach.model.InvalidNameException;
import pl.polsl.lab1.krzysztof.gach.model.Player;
import pl.polsl.lab1.krzysztof.gach.view.MessageBox;

/**
 * The ArgumentParser class is responsible for parsing command line arguments from a given input string.
 * It extracts arguments that follow a specific pattern defined by the regular expression.
 * 
 * @author Krzysztof Gach
 * @version 1.1
 */
public class ArgumentParser {
    private final String regex; // Predefined regex pattern for matching arguments
    private final Game game = Game.getInstance();
    
    /**
     * Constructs an ArgumentParser with a predefined regex pattern for argument matching.
     * Example matches: 
     * - "-s 10"
     * - "-p1 player1"
     * - "-p2 player2"
     */
    public ArgumentParser(){
        regex = "(-[\\d\\w]*\\s[\\d\\w]*)*";
    }
    
    /**
     * Parses the given input string and extracts arguments matching the defined pattern.
     *
     * @param input the input string containing arguments to be parsed
     * @return a list of arguments with values extracted from the input string. Returns an empty list if the input is null or empty.
     */
    public List<String> parseArguments(String input) {
        List<String> arguments = new ArrayList<>();
        
        // Return an empty list if the input is empty
        if (input == null || input.trim().isEmpty()) return arguments;
        
        // Compile the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        
        // Find and print all matches
        while (matcher.find()) {
            if(matcher.group().isEmpty()) continue;
                
            arguments.add(matcher.group());
        }
        
        return arguments;
    }
    
    public int checkParams(String[] args, JFrame frame){
        
        List<String> params = parseArguments(String.join(" ", args));
        
        var messageBox = new MessageBox(frame); 
        
        for(int i = 0; i < params.size(); i++){
            String[] splittedParams = params.get(i).split(" ");
            
            if(params.get(i).startsWith("-s")){
                setValidSize(splittedParams.length > 1 ? splittedParams[1] : null, messageBox); 
            }
            
            if(params.get(i).startsWith("-p1")){
                setValidPlayer(splittedParams.length > 1 ? splittedParams[1] : null, "X", messageBox);
            }
            
            if(params.get(i).startsWith("-p2")){
                setValidPlayer(splittedParams.length > 1 ? splittedParams[1] : null, "O", messageBox);
            }
        }
            
        if(game.getBoard().size() == 0){
            var input = messageBox.showInputDialog("Set board size: ", "Missing board size");
            
            if(input == null) return -1;
            
            setValidSize(input, messageBox); 
        }
        
        if(game.getPlayers().isEmpty()){
            String[] player1Data = messageBox.showTwoInputDialog("Enter player1 name:", "Enter player1 symbol", "Player Names");
            
            if(player1Data == null) return -1;
            
            setValidPlayer(player1Data[0], player1Data[1], messageBox);
            
            String[] player2Data = messageBox.showTwoInputDialog("Enter player2 name:", "Enter player2 symbol", "Player Names");
            
            if(player2Data == null) return -1;
            
            setValidPlayer(player2Data[0], player2Data[1], messageBox);
        }
        
        if(game.getPlayers().size() == 1) {
            String[] player2Data = messageBox.showTwoInputDialog("Enter player2 name:", "Enter player2 symbol", "Player Names");
            
            if(player2Data == null) return -1;
            
            setValidPlayer(player2Data[0], player2Data[1], messageBox);
        }
        
        return 0;
    }
        
    private void setValidSize(String sizeInput, MessageBox messageBox) {
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
    
    private void setValidPlayer(String name, String symbol, MessageBox messageBox) {
        while (symbol == null || symbol.length() != 1) {
            symbol = messageBox.showInputDialog("Invalid symbol. Please enter a single character symbol:", "Invalid Symbol");
        }

        Player player = new Player(name, symbol);
        boolean nameValid = false;

        while (!nameValid) {
            try {
                player.checkName();
                nameValid = true;
            } catch (InvalidNameException e) {
                name = messageBox.showInputDialog(e.getMessage(), "Invalid Name");
                player = new Player(name, symbol);
            }
        }
    
        game.addPlayer(player);

    }
}