package pl.polsl.lab1.krzysztof.gach.view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ButtonAction {
    private final JButton button;

    public ButtonAction(String title, ActionListener action) {
        button = new JButton(title);
        button.addActionListener(action);
        
        button.setFont(new Font("Arial", Font.BOLD, 16));
        
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.DARK_GRAY, 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });
    }

    public JButton getButton() {
        return button;
    }
}