package pl.polsl.lab1.krzysztof.gach.view;

import java.awt.event.*;
import javax.swing.*;
import pl.polsl.lab1.krzysztof.gach.controller.Game;
import pl.polsl.lab1.krzysztof.gach.controller.GameState;

/**
 *
 * @author Krzysztof Gach
 * @version 1.1
 */
public class OptionsButton implements ButtonAction{

    @Override
    public JButton createButton(Window window) {
        JButton optionsButton = new JButton("Options");
        optionsButton.addActionListener((ActionEvent e) -> {
            Game.getInstance().setGameState(GameState.MENU_OPTIONS);
//            window.openWindow(new OptionsWindow());
        });
        return optionsButton;
    }
    
}
