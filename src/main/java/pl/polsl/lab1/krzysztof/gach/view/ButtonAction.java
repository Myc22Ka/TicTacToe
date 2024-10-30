package pl.polsl.lab1.krzysztof.gach.view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ButtonAction {
    private final JButton button;
    private final Color defaultColor = new Color(70, 130, 180);
    private final Color hoverColor = new Color(100, 149, 237);

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
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(defaultColor);
            }
        });
    }

    public void setSelected(boolean selected) {
        if (selected) {
            button.setBackground(hoverColor);
        } else {
            button.setBackground(defaultColor);
        }
    }

    public JButton getButton() {
        return button;
    }
}
