package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pl.polsl.lab1.krzysztof.gach.controller.Game;

public class Button extends JButton implements ActionListener {
    private final int row;
    private final int col;
    private final Game game;
    private final int DEFAULT_SIZE = 100;
    private final Color color = new Color(50, 54, 65, 150);

    public Button(int row, int col, Game game) {
        this.row = row;
        this.col = col;
        this.game = game;
        

        // Custom appearance settings
        setFont(new Font("Arial", Font.BOLD, 40));
        setBackground(Color.decode("#323641"));
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Accessibility settings
        setToolTipText("Row " + (row + 1) + ", Column " + (col + 1));
        getAccessibleContext().setAccessibleDescription("Button at Row " + (row + 1) + ", Column " + (col + 1));

        addActionListener(this);

        // Set default size
        setMinimumSize(new Dimension(DEFAULT_SIZE, DEFAULT_SIZE));
        setPreferredSize(new Dimension(DEFAULT_SIZE, DEFAULT_SIZE));
        setMaximumSize(new Dimension(DEFAULT_SIZE, DEFAULT_SIZE));

        // Add hover effect
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(color);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(new Color(50, 54, 65));
            }
    
            public void mousePressed(java.awt.event.MouseEvent evt) {
                setBackground(new Color(40, 44, 54));
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBackground(color);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!getText().equals("")) return; // Check if button is already clicked

        game.nextTurn();
        setText(game.getCurrentPlayer().getSymbol()); // Set the symbol for the current player
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
