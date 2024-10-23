package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.*;
import java.awt.*;
import pl.polsl.lab1.krzysztof.gach.controller.Game;

/**
 * The Buttons class creates a panel with buttons for the TicTacToe game.
 */
public class Buttons extends JPanel {
    private final JButton[][] buttons = new JButton[3][3];
    private final Game game = Game.getInstance();

    public Buttons() {
        setLayout(new GridBagLayout());
        initButtons();
    }

    private void initButtons() {
         GridBagConstraints gbc = new GridBagConstraints();

        for (int row = 0; row < game.getBoard().size(); row++) {
            for (int col = 0; col < game.getBoard().size(); col++) {
                Button button = new Button(row, col, game);
                buttons[row][col] = button;

                gbc.gridx = col;
                gbc.gridy = row;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                add(button, gbc);
            }
        }
    }

    public JButton[][] getButtons() {
        return buttons;
    }
}
