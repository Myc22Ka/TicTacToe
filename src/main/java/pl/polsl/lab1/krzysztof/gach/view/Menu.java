package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Menu extends Window {
    private Collection<ButtonAction> buttons;
    private JPanel contentPanel; // This will be the main content panel

    @Override
    protected void setupWindow() {
        this.contentPanel = new JPanel();
        this.contentPanel.setLayout(new BorderLayout()); // Set layout here
        add(contentPanel); // Add contentPanel to the JFrame
        
        buttons = new ArrayList<>();

        // Add button actions to the collection
        buttons.add(new StartButton());
        buttons.add(new OptionsButton());
        buttons.add(new ExitButton());
        // You can add more buttons here...

        // Create and configure the title label
        JLabel titleLabel = new JLabel("Main Menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Add title label to contentPanel
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Create buttons panel and set its layout
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(0, 1, 10, 10));

        // Create buttons and add them to the buttons panel
        for (ButtonAction buttonAction : buttons) {
            buttonsPanel.add(buttonAction.createButton(this)); // Pass current window
        }

        // Add buttons panel to the contentPanel
        contentPanel.add(buttonsPanel, BorderLayout.CENTER);
    }

    // Method to expose the content panel
    @Override
    protected JPanel getContentPanel() {
        return contentPanel; // Return the main content panel
    }
}
