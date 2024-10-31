package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import pl.polsl.lab1.krzysztof.gach.controller.Game;

public class CellButton extends JButton {
    private final Game game = Game.getInstance();
    
    private final Color defaultColor = new Color(70, 130, 180);
    private final Color hoverColor = new Color(100, 149, 237);
    private final int x;
    private final int y;

    public CellButton(String value, int x, int y) {
        super(value);
        this.x = x;
        this.y = y;

        setFont(new Font("Arial", Font.BOLD, 40));

        setFocusPainted(false);
        setBackground(defaultColor);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setToolTipText("Cell (" + x + ", " + y + ") - Click to place your symbol");
        getAccessibleContext().setAccessibleDescription("Cell to place your symbols");
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(hoverColor);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(defaultColor);
            }
        });

        addActionListener((ActionEvent e) -> {
            var playerSymbol = game.getCurrentPlayer().getSymbol();
            
            game.getBoard().updateBoard(x, y, playerSymbol);
            setText(playerSymbol);
            
            setEnabled(false);
            setForeground(Color.WHITE);
            UIManager.put("Button.disabledText", new ColorUIResource(Color.WHITE));
            
            game.nextTurn();
        });
    }
}
