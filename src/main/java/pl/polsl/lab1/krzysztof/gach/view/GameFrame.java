package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import pl.polsl.lab1.krzysztof.gach.controller.Game;
import pl.polsl.lab1.krzysztof.gach.model.Player;

/**
 * Represents the main game frame for the Tic-Tac-Toe application, managing the game board and UI elements.
 * 
 * @author Krzysztof Gach
 * @version 1.0
 */
public class GameFrame extends Window {
    private final Game game = Game.getInstance(); // Instance of the Game to manage game state
    private final List<JLabel> playerScoreLabels; // Labels to display players' scores
    private final List<JLabel> playerNameLabels; // Labels to display players' names and symbols
    private ButtonAction nextRound; // Button for proceeding to the next round
    private final Color defaultColor = new Color(70, 130, 180); // Default color for UI components
    private final List<List<CellButton>> gameButtons; // 2D array of CellButton representing the game board
    
    /**
     * Constructs a GameFrame with a specified JFrame.
     *
     * @param frame the parent JFrame
     */
    public GameFrame(JFrame frame) {
        super(frame);        
        contentPanel.setLayout(new GridLayout(1, 2));
        gameButtons = new ArrayList<List<CellButton>>();
        playerScoreLabels = new ArrayList<JLabel>();
        playerNameLabels = new ArrayList<JLabel>();

        JPanel leftPanel = createBoard();
        contentPanel.add(leftPanel);
        
        var rightPanel = createRightPanel();
        contentPanel.add(rightPanel);

        setPanel(contentPanel);   
    }
    
    /**
     * Creates the game board panel with CellButtons for each cell.
     *
     * @return JPanel representing the game board
     */
    private JPanel createBoard() {
        JPanel boardPanel = new JPanel();
        int boardSize = game.getBoard().size();
        boardPanel.setLayout(new GridLayout(boardSize, boardSize));

        gameButtons.clear();
        for (int i = 0; i < boardSize; i++) {
            var row = new ArrayList<CellButton>();
            for (int j = 0; j < boardSize; j++) {
                CellButton button = new CellButton("", i, j, this);
                boardPanel.add(button);
                row.add(button);
            }
            gameButtons.add(row);
        }

        return boardPanel;
    }
    
    /**
     * Disables all buttons on the game board.
     */
    public void disableAllButtons() {
        for (List<CellButton> row : gameButtons) {
            for (CellButton button : row) {
                button.setEnabled(false);
            }
        }
    }
    
    /**
     * Creates the right panel containing game controls and player information.
     *
     * @return JPanel for the right side of the game frame
     */
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
    
    /**
     * Creates a back button to return to the main menu.
     *
     * @return JPanel containing the back button
     */
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
    
    /**
     * Navigates back to the main menu and clears the game board.
     */
    private void goBackToMenu() {
        MainMenu menu = new MainMenu(frame);
        menu.refresh();
        game.getBoard().clear();
    }
    
    /**
     * Creates a button to proceed to the next round.
     *
     * @return JPanel containing the next round button
     */
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
    
    /**
     * Proceeds to the next round and refreshes the UI.
     */
    private void nextRound(){
        game.nextRound();
        this.refresh();
    }
    
    /**
     * Creates a label displaying the current round number.
     *
     * @return JPanel containing the round label
     */
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
    
    /**
     * Creates a panel displaying the names and symbols of the players.
     *
     * @return JPanel containing player names and symbols
     */
    private JPanel createPlayerNamesPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 3, 0));

        var players = game.getPlayers();
        playerNameLabels.clear(); 

        for (int i = 0; i < players.size(); i++) {
            JLabel label = new JLabel(players.get(i).getName() + ": " + players.get(i).getSymbol());
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            label.setToolTipText("Player name and symbol");
            label.getAccessibleContext().setAccessibleDescription("Label to show player name and symbol");
        
            playerNameLabels.add(label);
            panel.add(label);
        }

        highlightCurrentPlayer();
        
        return panel;
    }
    
     /**
     * Creates a label for displaying the title "Score".
     *
     * @return JPanel containing the score label
     */
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
    
    /**
     * Creates a panel displaying the scores of the players.
     *
     * @return JPanel containing player scores
     */
    private JPanel createPlayersScorePanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 3, 0));

        var players = game.getPlayers();
        playerScoreLabels.clear();

        for (int i = 0; i < players.size(); i++) {
            JLabel label = new JLabel(String.valueOf(players.get(i).getScore()));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            label.setOpaque(true);
            label.setBackground(defaultColor);
            label.setToolTipText("Player score");
            label.getAccessibleContext().setAccessibleDescription("Label to show player score");

            playerScoreLabels.add(label);
            panel.add(label);
        }

        return panel;
    }
    
    /**
     * Highlights the current player's name in the UI.
     */
    public void highlightCurrentPlayer() {
        var players = game.getPlayers();
        int currentPlayerIndex = game.getCurrentPlayer().getId();

        Color highlightedBackground = new Color(70, 130, 180, 120);
    
        for (int i = 0; i < players.size(); i++) {
            playerNameLabels.get(i).setOpaque(true);
            playerNameLabels.get(i).setBackground(defaultColor);
        }

        playerNameLabels.get(currentPlayerIndex).setBackground(highlightedBackground);

        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    /**
     * Highlights the next round button.
     */
    public void highlightNextRound(){
        nextRound.getButton().setBackground(new Color(70, 130, 180, 120));
    }
    
     /**
     * Highlights the winner of the round.
     * @param player represents player that won round
     */
    public void highlightWinScore(Player player) {
        Color highlightedBackground = new Color(70, 130, 180, 120);
        
        updateScores();

        var score = Integer.toString(player.getScore());
        
        playerScoreLabels.forEach(scoreLabel -> {
            System.out.println(scoreLabel.getText() +" | "+ score);
            
            game.getPlayers().stream()
                .filter(p -> p.getId() == player.getId() && scoreLabel.getText().equals(score))
                .findFirst()
                .ifPresent(matchingPlayer -> {
                    scoreLabel.setOpaque(true);
                    scoreLabel.setBackground(highlightedBackground);
                });
        });

        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    /**
     * Updates the score labels for each player.
     */
    public void updateScores() {
        var players = game.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            playerScoreLabels.get(i).setText(String.valueOf(players.get(i).getScore()));
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
