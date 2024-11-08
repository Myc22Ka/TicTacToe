package pl.polsl.lab1.krzysztof.gach.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
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
        List<String> arguments = new ArrayList<String>();
        
        if (input == null || input.trim().isEmpty()) return arguments;
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        
        while (matcher.find()) {
            if(matcher.group().isEmpty()) continue;
                
            arguments.add(matcher.group());
        }
        
        return arguments;
    }
    
    /**
    * Checks and processes parameters passed to the application.
    *
    * This function analyzes the application's input parameters, such as board size and player settings,
    * and validates them. If a parameter is invalid, it displays an appropriate 
    * message in a dialog box.
    * 
    * @param args  an array of input arguments passed to the application
    * @param frame the {@link JFrame} object used as the main window for displaying validation messages
    */
    public void checkParams(String[] args, JFrame frame){
        Validator validator = new Validator();
        
        var params = parseArguments(String.join(" ", args));
        
        var messageBox = new MessageBox(frame); 
        
        for(int i = 0; i < params.size(); i++){
            String[] splittedParams = params.get(i).split(" ");
            
            if(params.get(i).startsWith("-s")){
                validator.setValidSize(splittedParams.length > 1 ? splittedParams[1] : null, messageBox); 
            }
            
            if(params.get(i).startsWith("-p1")){
                validator.setValidPlayer(splittedParams.length > 1 ? splittedParams[1] : null, "X", messageBox);
            }
            
            if(params.get(i).startsWith("-p2")){
                validator.setValidPlayer(splittedParams.length > 1 ? splittedParams[1] : null, "O", messageBox);
            }
        }
    }
}