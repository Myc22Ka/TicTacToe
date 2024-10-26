package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.JButton;
import java.awt.event.ActionListener;

public class ButtonAction {
    private JButton button;

    public ButtonAction(String title, ActionListener action) {
        button = new JButton(title);
        button.addActionListener(action);
    }

    public JButton getButton() {
        return button;
    }
}