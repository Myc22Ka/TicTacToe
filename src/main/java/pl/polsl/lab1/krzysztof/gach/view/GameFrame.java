package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import pl.polsl.lab1.krzysztof.gach.controller.Game;

public class GameFrame extends Window {
    private final Game game = Game.getInstance();
    private JLabel[] playerScoreLabels;
    private JLabel[] playerNameLabels;
    private ButtonAction nextRound;
    private final Color defaultColor = new Color(70, 130, 180);
    private CellButton[][] gameButtons;
    
    public GameFrame(JFrame frame) {
        super(frame);        
        contentPanel.setLayout(new GridLayout(1, 2));
        gameButtons = new CellButton[game.getBoard().size()][game.getBoard().size()];

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
                gameButtons[i][j] = new CellButton("", i, j, this);
                boardPanel.add(gameButtons[i][j]);
            }
        }

        this.gameButtons = gameButtons;

        return boardPanel;
    }
    
    public void disableAllButtons() {
    for (CellButton[] row : gameButtons) {
        for (CellButton button : row) {
            button.setEnabled(false);
        }
    }
}
    
    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(6, 1, 0, 3));
        
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
        game.getBoard().clear();
    }
    
    private JPanel createNextRoundButton(){
        JPanel panel = new JPanel(new BorderLayout());
        
        nextRound = new ButtonAction("Next Round", e -> nextRound()); 
        
        InputMap inputMap = nextRound.getButton().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = nextRound.getButton().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("control R"), "nextRound");
        actionMap.put("nextRound", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextRound();
            }
        });
        
        panel.add(nextRound.getButton());
        
        return panel;
    }
    
    private void nextRound(){
        game.nextRound();
        this.refresh();
    }
    
    private JPanel createRoundLabel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel label = new JLabel("Round: " + game.getRound());
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setOpaque(true);
        label.setBackground(defaultColor);
        
        label.setToolTipText("Label to show your current round");
        label.getAccessibleContext().setAccessibleDescription("Label to show your current round");
        
        panel.add(label);
    
        return panel;
    }
    
    private JPanel createPlayerNamesPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 3, 0));

        var players = game.getPlayers();
        playerNameLabels = new JLabel[players.size()]; 

        for (int i = 0; i < players.size(); i++) {
            JLabel label = new JLabel(players.get(i).getName() + ": " + players.get(i).getSymbol());
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            label.setToolTipText("Player name and symbol");
            label.getAccessibleContext().setAccessibleDescription("Label to show player name and symbol");
        
            playerNameLabels[i] = label;
            panel.add(label);
        }

        highlightCurrentPlayer();
        
        return panel;
    }
    
    private JPanel createScoreLabel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        JLabel label = new JLabel("Score");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setOpaque(true);
        label.setBackground(defaultColor);
        label.setToolTipText("Score section");
        label.getAccessibleContext().setAccessibleDescription("Label to show score section");
        
        panel.add(label);
    
        return panel;
    }
    
    private JPanel createPlayersScorePanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 3, 0));

        var players = game.getPlayers();
        playerScoreLabels = new JLabel[players.size()];

        for (int i = 0; i < players.size(); i++) {
            JLabel label = new JLabel(String.valueOf(players.get(i).getScore()));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            label.setOpaque(true);
            label.setBackground(defaultColor);
            label.setToolTipText("Player score");
            label.getAccessibleContext().setAccessibleDescription("Label to show player score");

            playerScoreLabels[i] = label;
            panel.add(label);
        }

        return panel;
    }
    
    public void highlightCurrentPlayer() {
        var players = game.getPlayers();
        int currentPlayerIndex = game.getCurrentPlayer().getId();

        Color highlightedBackground = new Color(70, 130, 180, 120);
    
        for (int i = 0; i < players.size(); i++) {
            playerNameLabels[i].setOpaque(true);
            playerNameLabels[i].setBackground(defaultColor);
        }

        playerNameLabels[currentPlayerIndex].setBackground(highlightedBackground);

        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    public void highlightNextRound(){
        nextRound.getButton().setBackground(new Color(70, 130, 180, 120));
    }
    
    public void updateScores() {
        var players = game.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            playerScoreLabels[i].setText(String.valueOf(players.get(i).getScore()));
        }
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
