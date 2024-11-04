package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import pl.polsl.lab1.krzysztof.gach.controller.Game;
import pl.polsl.lab1.krzysztof.gach.controller.GameState;
import pl.polsl.lab1.krzysztof.gach.controller.Validator;

/**
 * The MainMenu class represents the main menu of the application,
 * allowing users to navigate to different functionalities such as starting a new game,
 * continuing an existing game, viewing the leaderboard, adjusting options, and exiting the application.
 * 
 * @author Krzysztof Gach
 * @version 1.0
 */
public class MainMenu extends Window {
    private final ArrayList<ButtonAction> menuButtons; // List to hold the menu buttons
    private final Game game = Game.getInstance(); // Singleton instance of the Game controller
    private int selectedIndex = 0; // Variable to track the currently selected button index

    /**
     * Constructs a new MainMenu with the specified frame.
     *
     * @param frame The JFrame that this menu will be associated with.
     */
    public MainMenu(JFrame frame) {
        super(frame);
        menuButtons = new ArrayList<>();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel menuLabel = new JLabel("MENU", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 32));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(menuLabel, gbc);

        // Initialize buttons with their actions
        menuButtons.add(new ButtonAction("New Game", e -> showGame()));
        menuButtons.add(new ButtonAction("Continue", e -> continueGame()));
        menuButtons.add(new ButtonAction("Leaderboard", e -> showLeaderBoard()));
        menuButtons.add(new ButtonAction("Options", e -> showOptions()));
        menuButtons.add(new ButtonAction("Exit", e -> exit()));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        for (ButtonAction buttonAction : menuButtons) {
            JButton button = buttonAction.getButton();
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setPreferredSize(new Dimension(200, 40));
            buttonPanel.add(button);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        
        addShortcuts();
        
        gbc.gridy = 1;
        gbc.weighty = 2.0;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(buttonPanel, gbc);
        
        setPanel(contentPanel);
    }
    
    /**
     * Adds key bindings for shortcuts CTRL + 1 to CTRL + 5.
     */
    private void addShortcuts() {
        for (int i = 0; i < menuButtons.size(); i++) {
            InputMap inputMap = menuButtons.get(i).getButton().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = menuButtons.get(i).getButton().getActionMap();

            // Define the key stroke for CTRL + (number)
            KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_1 + i, InputEvent.CTRL_DOWN_MASK);
            inputMap.put(keyStroke, "activateButton" + i);
            
            final int index = i; // Create a final variable to capture the current index
            actionMap.put("activateButton" + i, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedIndex = index; // Use the final variable
                    activateSelectedButton(); // Activate button
                }
            });
        }
    }

    /**
     * Activates the currently selected button.
     */
    private void activateSelectedButton() {
        menuButtons.get(selectedIndex).getButton().doClick(); // Activate the button corresponding to selectedIndex
    }

    /**
     * Shows the game when "New Game" is selected.
     */
    private void showGame() {  
        var validator = new Validator();
        var status = validator.getGameStatus(frame);
        if(status != Validator.ValidationStatus.VALID) return;
        game.setGameState(GameState.PLAY);     
        this.changeWindow();
    }

    /**
     * Placeholder method for continuing a game.
     */
    private void continueGame() {
        var messageBox = new MessageBox(frame);
        messageBox.showInfoMessage("Continue game...", "Info Panel");
    }

    /**
     * Shows the leaderboard when selected.
     */
    private void showLeaderBoard() {
        game.setGameState(GameState.MENU_LEADERBOARD);
        this.changeWindow();
    }

    /**
     * Shows the options menu when selected.
     */
    private void showOptions() {
        game.setGameState(GameState.MENU_OPTIONS);
        this.changeWindow();
    }

    /**
     * Exits the application when selected.
     */
    private void exit() {
        game.setGameState(GameState.EXIT);
        this.changeWindow();
    }

    @Override
    public void refresh() {
        setPanel(contentPanel);
    }
}
