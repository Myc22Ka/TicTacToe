package pl.polsl.lab1.krzysztof.gach.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kris
 */
public class ArgumentParser {
    public List<String> parseArguments(String input) {
        List<String> arguments = new ArrayList<>();
        
        if (input == null || input.trim().isEmpty()) {
            return arguments;  // Return an empty list if the input is empty
        }
        
        String regex = "(-[\\d\\w]*\\s[\\d\\w]*)*";
        
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
