package pl.polsl.lab1.krzysztof.gach.view;

import java.awt.event.ActionEvent;
import javax.swing.*;
import pl.polsl.lab1.krzysztof.gach.controller.Game;
import pl.polsl.lab1.krzysztof.gach.controller.GameState;

/**
 *
 * @author Krzysztof Gach
 * @version 1.1
 */
public class StartButton implements ButtonAction {
    
    @Override
    public JButton createButton(Window window) {
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener((ActionEvent e) -> {
            Game.getInstance().setGameState(GameState.PLAY);
            
            GameFrame gameFrame = new GameFrame();
            window.updateContent(gameFrame.getContentPanel()); 
        });
        return startButton;
    }
}
