package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import pl.polsl.lab1.krzysztof.gach.controller.Game;

/**
 * Represents a cell button in the Tic-Tac-Toe game that players can click to place their symbols.
 * 
 * @author Krzysztof Gach
 * @version 1.0
 */
public class CellButton extends JButton {
    /**
     * Instance of the Game for managing game logic.
     */
    private final Game game = Game.getInstance();

    /**
     * Default background color for the UI elements.
     */
    private final Color defaultColor = new Color(70, 130, 180);

    /**
     * Background color used when an element is hovered over.
     */
    private final Color hoverColor = new Color(100, 149, 237);

    /**
     * Constructs a CellButton with a specified value, position, and reference to the game frame.
     *
     * @param value the initial text displayed on the button (e.g., empty or player's symbol)
     * @param x the x-coordinate of the cell in the game grid
     * @param y the y-coordinate of the cell in the game grid
     * @param gameFrame reference to the GameFrame for UI updates
     */
    public CellButton(String value, int x, int y, GameFrame gameFrame) {
        super(value);

        setFont(new Font("Arial", Font.BOLD, 40));

        setFocusPainted(false);
        setBackground(defaultColor);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setToolTipText("Cell (" + x + ", " + y + ") - Click to place your symbol");
        getAccessibleContext().setAccessibleDescription("Cell to place your symbols");
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(hoverColor);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(defaultColor);
            }
        });

        addActionListener((ActionEvent e) -> {
            var playerSymbol = game.getCurrentPlayer().getSymbol();
            
            game.getBoard().updateBoard(x, y, playerSymbol);
            setText(playerSymbol);
            
            game.nextTurn();
            
            var won = game.getBoard().checkWin();
            
            if(game.getBoard().isBoardFull() || !won.isEmpty()) {
                gameFrame.highlightNextRound();
            }
            
            if(!won.isEmpty()){
                gameFrame.disableAllButtons();
                
                for(var player : game.getPlayers()){
                    if(player.getSymbol().equals(won)){
                        player.addScore(100);
                        gameFrame.highlightWinScore(player);
                    }
                }
            }
            
            game.getCurrentPlayer().setScore(game.getCurrentPlayer().getScore() + 10);
            gameFrame.updateScores();
            gameFrame.highlightCurrentPlayer();
            
            setEnabled(false);
            setForeground(Color.WHITE);
            UIManager.put("Button.disabledText", new ColorUIResource(Color.WHITE));
        });
    }
}
