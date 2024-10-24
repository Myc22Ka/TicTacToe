package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainMenu extends Window {
    private LinkedHashMap<String, JButton> menuButtons;

    public MainMenu(JFrame frame) {
        super(frame);
        menuButtons = new LinkedHashMap<>();
        contentPanel.setLayout(new GridLayout(4, 1));

        menuButtons.put("New Game", new JButton("New Game"));
        menuButtons.put("Continue", new JButton("Continue"));
        menuButtons.put("Options", new JButton("Options"));
        menuButtons.put("Exit", new JButton("Exit"));

        for (Map.Entry<String, JButton> entry : menuButtons.entrySet()) {
            JButton button = entry.getValue();
            button.addActionListener(new MenuButtonListener(entry.getKey()));
            contentPanel.add(button);
        }

        setPanel(contentPanel);
    }

    private class MenuButtonListener implements ActionListener {
        private String action;

        public MenuButtonListener(String action) {
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (action) {
                case "New Game":
                    GameFrame gameFrame = new GameFrame(frame);
                    gameFrame.refresh();
                    break;
                case "Continue":
                    // Implement continue game functionality
                    System.out.println("Continue game clicked");
                    break;
                case "Options":
                    // Implement options functionality
                    System.out.println("Options clicked");
                    break;
                case "Exit":
                    System.exit(0);
                    break;
            }
        }
    }

    @Override
    public void refresh() {
        setPanel(contentPanel);
    }
}
