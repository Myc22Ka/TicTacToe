package pl.polsl.lab1.krzysztof.gach.view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import pl.polsl.lab1.krzysztof.gach.model.AudioManager;

/**
 * Represents a customizable button with action listener and hover effects.
 * The button plays a sound when hovered over and has a default and hover color.
 * 
 * @author Krzysztof Gach
 * @version 1.0
 * 
 */
public class ButtonAction {
    private final JButton button;
    private final Color defaultColor = new Color(70, 130, 180);
    private final Color hoverColor = new Color(100, 149, 237);
    private final AudioManager audioManager = AudioManager.getInstance();

    /**
     * Constructs a ButtonAction instance with the specified title and action listener.
     *
     * @param title the text to display on the button
     * @param action the action listener to handle button clicks
     */
    public ButtonAction(String title, ActionListener action) {
        button = new JButton(title);
        button.addActionListener(action);
        
        button.setFont(new Font("Arial", Font.BOLD, 16));
        
        button.setBackground(defaultColor);
        button.setForeground(Color.WHITE);
        
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);

        button.setToolTipText("Click to " + title.toLowerCase());
        button.getAccessibleContext().setAccessibleDescription("Button to " + title.toLowerCase());

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor); 
                audioManager.loadAudio("./assets/blipSelect.wav");
                audioManager.play();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(defaultColor);
            }
        });
    }

    /**
     * Sets the selected state of the button, changing its color based on the state.
     *
     * @param selected true if the button should be in the selected state, false otherwise
     */
    public void setSelected(boolean selected) {
        if (selected) {
            button.setBackground(hoverColor);
        } else {
            button.setBackground(defaultColor);
        }
    }

    /**
     * Retrieves the JButton instance associated with this ButtonAction.
     *
     * @return the JButton associated with this ButtonAction
     */
    public JButton getButton() {
        return button;
    }
}
