package pl.polsl.lab1.krzysztof.gach.model;

/**
 * Custom exception class to handle invalid names.
 * 
 * This class extends the Exception class and is used to throw custom
 * exceptions related to invalid player names in the application.
 * 
 * @author Krzysztof Gach
 * @version 1.0
 */
public class InvalidNameException extends Exception {

    /**
     * Constructor for InvalidNameException.
     * 
     * @param message The error message to be displayed when the exception is thrown.
     */
    public InvalidNameException(String message) {
        super(message);
    }
}

