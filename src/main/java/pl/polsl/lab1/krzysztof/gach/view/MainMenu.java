package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import pl.polsl.lab1.krzysztof.gach.controller.Game;

public class MainMenu extends Window {
    private final Collection<ButtonAction> menuButtons;
    private final Game game = Game.getInstance();

    public MainMenu(JFrame frame) {
        super(frame);
        menuButtons = new ArrayList<>();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create and add menu label
        JLabel menuLabel = new JLabel("MENU", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 32));
        
        // Configure GridBagConstraints for the menu label
        gbc.gridx = 0; // Column
        gbc.gridy = 0; // Row
        gbc.weighty = 1.0; // Allows the label to be pushed down
        gbc.insets = new Insets(20, 0, 20, 0); // Top, left, bottom, right padding
        gbc.anchor = GridBagConstraints.CENTER; // Center the label
        contentPanel.add(menuLabel, gbc);

        // Create and add buttons
        menuButtons.add(new ButtonAction("New Game", e -> showGame()));
        menuButtons.add(new ButtonAction("Continue", e -> continueGame()));
        menuButtons.add(new ButtonAction("Leaderboard", e -> showLeaderBoard()));
        menuButtons.add(new ButtonAction("Options", e -> showOptions()));
        menuButtons.add(new ButtonAction("Exit", e -> exit()));

        // Create button panel for better alignment
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        for (ButtonAction buttonAction : menuButtons) {
            JButton button = buttonAction.getButton();
            button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center each button
            button.setPreferredSize(new Dimension(200, 40)); // Set a preferred size for buttons
            buttonPanel.add(button);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between buttons
        }

        // Add button panel to contentPanel
        gbc.gridy = 1; // Move to the next row
        gbc.weighty = 2.0; // Give the button panel more weight
        gbc.anchor = GridBagConstraints.CENTER; // Center the button panel
        contentPanel.add(buttonPanel, gbc);

        // Adjust the grid to push everything down by a third of the Y axis
        gbc.gridy = 2; // Push everything down to the third row
        gbc.weighty = 3.0; // Give it more space
        contentPanel.add(new JPanel(), gbc); // Empty panel for spacing

        setPanel(contentPanel);
    }

    private void showGame() {       
        var status = game.startGame(frame);
        
        if(status == -1) {
            game.getPlayers().clear();
            return;
        }
        
        GameFrame gameFrame = new GameFrame(frame);
        gameFrame.refresh();
    }

    private void continueGame() {
        System.out.println("Continue game...");
    }

    private void showLeaderBoard() {
        LeaderBoard leaderBoard = new LeaderBoard(frame);
        leaderBoard.refresh();
    }

    private void showOptions() {
        OptionsPanel optionsPanel = new OptionsPanel(frame);
        optionsPanel.refresh();
    }

    private void exit() {
        frame.dispose();
    }

    @Override
    public void refresh() {
        setPanel(contentPanel);
    }
}
