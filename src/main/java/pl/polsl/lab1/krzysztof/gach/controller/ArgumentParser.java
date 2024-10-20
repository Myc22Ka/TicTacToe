package pl.polsl.lab1.krzysztof.gach.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The ArgumentParser class is responsible for parsing command line arguments from a given input string.
 * It extracts arguments that follow a specific pattern defined by the regular expression.
 * 
 * @author Krzysztof Gach
 * @version 1.0
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
}
