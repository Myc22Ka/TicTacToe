package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pl.polsl.lab1.krzysztof.gach.controller.Game;

public class CellButton extends JButton {
    private final Game game = Game.getInstance();
    
    private final int x;
    private final int y;

    public CellButton(String value, int x, int y) {
        super(value);
        setFont(new Font("Arial", Font.BOLD, 40));
        setFocusPainted(false);
        this.x = x;
        this.y = y;
        addActionListener(new CellButtonListener());
    }

    private class CellButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            var playerSymbol = game.getCurrentPlayer().getSymbol();
            
            game.getBoard().updateBoard(x, y, playerSymbol);
            setText(playerSymbol);
            setEnabled(false);
            
            game.nextTurn();
        }
    }
}
