package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.*;
import pl.polsl.lab1.krzysztof.gach.controller.Game;

/**
 * The Window class serves as an abstract base for all game windows.
 * It manages the main JFrame and handles the transition between different game states.
 * 
 * @author Krzysztof Gach
 * @version 1.0
 */
public abstract class Window {
    /**
     * Singleton instance of the Game class.
     */
    private final Game game = Game.getInstance();

    /**
     * The main frame for the application.
     */
    protected JFrame frame;

    /**
     * The main content panel for the frame, used as the central container for UI components.
     */
    protected JPanel contentPanel;

    /**
     * Constructs a new Window associated with the specified JFrame.
     *
     * @param frame The JFrame that this window will use.
     */
    public Window(JFrame frame) {
        this.frame = frame;
        contentPanel = new JPanel();
        frame.setContentPane(contentPanel);
    }

    /**
     * Changes the current window based on the current game state.
     */
    public void changeWindow(){
        switch(game.getGameState()){
            case PLAY -> {
                GameFrame gameFrame = new GameFrame(frame);
                gameFrame.refresh();
            }
            case EXIT -> {
                frame.dispose();
            }
                
            case MENU -> {
                MainMenu mainMenu = new MainMenu(frame);
                mainMenu.refresh();
            }
                
            case MENU_OPTIONS -> {
                OptionsPanel optionsPanel = new OptionsPanel(frame);
                optionsPanel.refresh();
            }
            
            case MENU_LEADERBOARD -> {
                LeaderBoard leaderBoard = new LeaderBoard(frame);
                leaderBoard.refresh();
            }
        }
    }
    
    /**
     * Displays the application window.
     */
    public void display(){
        frame = new JFrame("App Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    
    /**
     * Refreshes the panel to ensure it displays the latest options.
     */
    public abstract void refresh();

    /**
     * Sets the content panel of the JFrame.
     *
     * @param panel The JPanel to set as the content pane.
     */
    protected void setPanel(JPanel panel) {
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }
}
