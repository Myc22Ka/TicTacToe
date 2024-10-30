package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import pl.polsl.lab1.krzysztof.gach.controller.Game;
import pl.polsl.lab1.krzysztof.gach.controller.GameState;
import pl.polsl.lab1.krzysztof.gach.controller.Validator;
import pl.polsl.lab1.krzysztof.gach.model.AudioManager;

public class MainMenu extends Window {
    private final ArrayList<ButtonAction> menuButtons;
    private final Game game = Game.getInstance();
    private final AudioManager audioManager = AudioManager.getInstance();
    
    private int selectedIndex = 0;

    public MainMenu(JFrame frame) {
        super(frame);
        menuButtons = new ArrayList<>();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel menuLabel = new JLabel("MENU", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 32));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(menuLabel, gbc);

        menuButtons.add(new ButtonAction("New Game", e -> showGame()));
        menuButtons.add(new ButtonAction("Continue", e -> continueGame()));
        menuButtons.add(new ButtonAction("Leaderboard", e -> showLeaderBoard()));
        menuButtons.add(new ButtonAction("Options", e -> showOptions()));
        menuButtons.add(new ButtonAction("Exit", e -> exit()));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        for (ButtonAction buttonAction : menuButtons) {
            JButton button = buttonAction.getButton();
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setPreferredSize(new Dimension(200, 40));
            buttonPanel.add(button);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        
        gbc.gridy = 1;
        gbc.weighty = 2.0;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(buttonPanel, gbc);
        
        JLabel instructionsLabel = new JLabel("Use Arrow Up/Down to navigate, Enter to select", SwingConstants.CENTER);
        instructionsLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        instructionsLabel.setForeground(Color.GRAY);
        
        gbc.gridy = 3;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(5, 0, 5, 0);
        contentPanel.add(instructionsLabel, gbc);

        gbc.gridy = 2;
        gbc.weighty = 3.0;
        contentPanel.add(new JPanel(), gbc);    
        
        setPanel(contentPanel);
      
        contentPanel.setFocusable(true);
        contentPanel.requestFocusInWindow();
        contentPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> moveSelectionUp();
                    case KeyEvent.VK_DOWN -> moveSelectionDown();
                    case KeyEvent.VK_ENTER -> activateSelectedButton();
                }
            }
        });
        
        updateButtonSelection();
    }

    private void playSwitchSound() {
        audioManager.loadAudio("./assets/blipSelect.wav");

        audioManager.play();
    }
    
    private void moveSelectionUp() {
        selectedIndex = (selectedIndex > 0) ? selectedIndex - 1 : menuButtons.size() - 1;
        playSwitchSound();
        updateButtonSelection();
    }

    private void moveSelectionDown() {
        selectedIndex = (selectedIndex < menuButtons.size() - 1) ? selectedIndex + 1 : 0;
        playSwitchSound();
        updateButtonSelection();
    }

    private void activateSelectedButton() {
        menuButtons.get(selectedIndex).getButton().doClick();
    }

    private void updateButtonSelection() {
        for (int i = 0; i < menuButtons.size(); i++) {
            ButtonAction buttonAction = menuButtons.get(i);
            buttonAction.setSelected(i == selectedIndex);
        }
    }

    private void showGame() {  
        var validator = new Validator();
        
        var status = validator.getGameStatus(frame);
        
        if(status != Validator.ValidationStatus.VALID) return;
        
        game.setGameState(GameState.PLAY);     
        this.changeWindow();
    }

    private void continueGame() {
        System.out.println("Continue game...");
    }

    private void showLeaderBoard() {
        game.setGameState(GameState.MENU_LEADERBOARD);
        this.changeWindow();
    }

    private void showOptions() {
        game.setGameState(GameState.MENU_OPTIONS);
        this.changeWindow();
    }

    private void exit() {
        game.setGameState(GameState.EXIT);
        this.changeWindow();
    }

    @Override
    public void refresh() {
        setPanel(contentPanel);
    }
}
