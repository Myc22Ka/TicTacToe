package pl.polsl.lab1.krzysztof.gach.view;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import pl.polsl.lab1.krzysztof.gach.controller.Game;
import pl.polsl.lab1.krzysztof.gach.controller.GameState;

/**
 *
 * @author kris
 */
public class ExitButton implements ButtonAction {
    @Override
    public JButton createButton(Window window) {
        JButton startButton = new JButton("Exit Game");
        startButton.addActionListener((ActionEvent e) -> {
            Game.getInstance().setGameState(GameState.EXIT);
            window.dispose();
        });
        return startButton;
    }
}
