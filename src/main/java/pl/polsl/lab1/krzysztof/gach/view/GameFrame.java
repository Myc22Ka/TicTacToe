package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameFrame extends Window {
    private JPanel contentPanel;
    
    @Override
    protected void setupWindow() {
        this.contentPanel = new JPanel(); // Initialize the contentPanel
        add(contentPanel);

        JPanel gamePanel = new JPanel();
        gamePanel.add(new JLabel("Game in Progress..."));

        JButton backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.addActionListener((ActionEvent e) -> {
            // Open the Menu when "Back to Menu" is clicked
            Menu menu = new Menu();
            menu.displayWindow(); // Show the menu window
        });

        gamePanel.add(backToMenuButton);

        contentPanel.add(gamePanel); // Add the gamePanel to the contentPanel
    }
    
    @Override
    protected JPanel getContentPanel(){
        return contentPanel;
    }
}
