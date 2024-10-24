package pl.polsl.lab1.krzysztof.gach.view;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends Window {
    public GameFrame(JFrame frame) {
        super(frame);
        contentPanel.setLayout(new BorderLayout());
        JButton switchButton = new JButton("Back to Menu");
        switchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu menu = new MainMenu(frame);
                menu.refresh();
            }
        });
        contentPanel.add(switchButton, BorderLayout.CENTER);
        setPanel(contentPanel);
    }

    @Override
    public void refresh() {
        setPanel(contentPanel);
    }
}
