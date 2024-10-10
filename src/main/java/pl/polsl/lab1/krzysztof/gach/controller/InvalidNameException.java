package pl.polsl.lab1.krzysztof.gach.controller;

/**
 * Custom exception class to handle invalid names.
 * 
 * This class extends the Exception class and is used to throw custom
 * exceptions related to invalid player names in the application.
 * 
 * @author Krzysztof Gach
 */
public class InvalidNameException extends Exception {

    /**
     * Constructor for InvalidNameException.
     * 
     * @param message The error message to be displayed when the exception is thrown.
     */
    public InvalidNameException(String message) {
        super(message); // Pass the message to the parent Exception class.
    }
}

