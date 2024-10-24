/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab1.krzysztof.gach.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import pl.polsl.lab1.krzysztof.gach.view.GameFrame;

/**
 *
 * @author kris
 */
class NewGameButton extends ButtonAction {
        private JFrame frame;

        public NewGameButton(JFrame frame) {
            super("New Game");
            this.frame = frame;
        }

        @Override
        protected ActionListener createActionListener() {
            return new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GameFrame gameFrame = new GameFrame(frame);
                    gameFrame.refresh();
                }
            };
        }
    }
