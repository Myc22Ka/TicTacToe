package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import pl.polsl.lab1.krzysztof.gach.controller.Game;

public class GameFrame extends Window {
    private final Game game = Game.getInstance();
    
    public GameFrame(JFrame frame) {
        super(frame);        
        contentPanel.setLayout(new GridLayout(1, 2));

        JPanel leftPanel = createBoard();
        contentPanel.add(leftPanel);
        
        var rightPanel = createRightPanel();
        contentPanel.add(rightPanel);

        setPanel(contentPanel);
    }
    
    private JPanel createBoard() {
        JPanel boardPanel = new JPanel();
        int boardSize = game.getBoard().size();
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));

        CellButton[][] gameButtons = new CellButton[boardSize][boardSize];


        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                gameButtons[i][j] = new CellButton("", i, j);
                boardPanel.add(gameButtons[i][j]);
            }
        }

        return boardPanel;
    }
    
    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(6, 1));
        
        rightPanel.add(createBackButton());
        rightPanel.add(createRoundLabel());
        rightPanel.add(createPlayerNamesPanel());
        rightPanel.add(createScoreLabel());
        rightPanel.add(createPlayersScorePanel());
        rightPanel.add(createNextRoundButton());

        return rightPanel;
    }
    
    private JPanel createBackButton(){
        JPanel panel = new JPanel(new BorderLayout());
        
        ButtonAction button = new ButtonAction("Back to Menu", e -> goBackToMenu());

        panel.add(button.getButton());
        
        InputMap inputMap = button.getButton().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = button.getButton().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("control B"), "backAction");
        actionMap.put("backAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackToMenu();
            }
        });
        
        return panel;
    }
    
    private void goBackToMenu() {
        MainMenu menu = new MainMenu(frame);
        menu.refresh();
    }
    
    private JPanel createNextRoundButton(){
        JPanel panel = new JPanel(new BorderLayout());
        
        ButtonAction button = new ButtonAction("Next Round", e -> {
            game.nextRound();
            this.refresh();
        }); 
        
        InputMap inputMap = button.getButton().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = button.getButton().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("control B"), "backAction");
        actionMap.put("backAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackToMenu();
            }
        });
        
        panel.add(button.getButton());
        
        return panel;
    }
    
    private JPanel createRoundLabel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel roundLabel = new JLabel("Round: " + game.getRound());
        roundLabel.setHorizontalAlignment(SwingConstants.CENTER);
        roundLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        panel.add(roundLabel);
    
        return panel;
    }
    
    private JPanel createPlayerNamesPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        
        var players = game.getPlayers();
        
        for(var player : players){
            JLabel label = new JLabel(player.getName() + ": " + player.getSymbol());
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            
            panel.add(label);
        }

        return panel;
    }
    
    private JPanel createScoreLabel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel label = new JLabel("Score");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        
        panel.add(label);
    
        return panel;
    }
    
    private JPanel createPlayersScorePanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        
        var players = game.getPlayers();
        
        for(var player : players){
            JLabel label = new JLabel(String.valueOf(player.getScore()));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            
            panel.add(label);
        }

        return panel;
    }
    
    @Override
    public void refresh() {
        contentPanel.removeAll();
        contentPanel.add(createBoard());
        contentPanel.add(createRightPanel());
        contentPanel.revalidate();
        contentPanel.repaint();
        
        setPanel(contentPanel);
    }
}
