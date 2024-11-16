package pl.polsl.lab1.krzysztof.gach.controller;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import pl.polsl.lab1.krzysztof.gach.model.CellAdnotation;
import pl.polsl.lab1.krzysztof.gach.view.MainMenu;

/**
 * The Controller class is responsible for managing the game flow and handling user input
 * for the console-based game. It interacts with the Game class to start the game,
 * process user actions, and update the game state.
 * 
 * @author Krzysztof Gach
 * @version 1.3
 */
public class Controller{  
    private final JFrame frame;     // varaible that holds the main JFrame for the app.
    
    /**
     * Private constructor that initializes the main JFrame for the game.
     */
    private Controller(){
        this.frame = new JFrame("Tic-Tac-Toe");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("./assets/favicon.png").getImage());
        
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        frame.setVisible(true);
    }
    
    /**
     * Retrieves the default value from the {@link CellAdnotation} annotation of the specified class.
     * 
     * @param clazz the class to check for the annotation
     * @return the default value from the annotation
     * @throws IllegalStateException if the class is not annotated with {@link CellAdnotation}
     */
    public static String getDefaultCellValue(Class<?> clazz) {
        if (clazz.isAnnotationPresent(CellAdnotation.class)) {
            var annotation = clazz.getAnnotation(CellAdnotation.class);
            return annotation.value();
        }
        throw new IllegalStateException("Class " + clazz.getName() + " is not annotated with @DefaultValue.");
    }
    
    /**
     * The main method serves as the entry point for the application.
     * It initializes the Game and Controller, starts the game, and manages
     * the game loop.
     *
     * @param args command-line arguments passed to the program
     * Expected parameters:
     *  -s: size of the game
     *  -p1: name of the first player
     *  -p2: name of the second player
     * 
     */
    public static void main(String[] args){      
        Game game = Game.getInstance();
        game.setArgs(args);       
        
        Controller controller = new Controller();
        
        ArgumentParser argumentParser = new ArgumentParser();
        argumentParser.checkParams(args, controller.frame);
        
        MainMenu mainMenu = new MainMenu(controller.frame);
    }
}
