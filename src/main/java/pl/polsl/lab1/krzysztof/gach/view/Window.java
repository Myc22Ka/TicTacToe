package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.*;
import pl.polsl.lab1.krzysztof.gach.controller.Game;
import pl.polsl.lab1.krzysztof.gach.controller.GameState;

public abstract class Window {
    private final Game game = Game.getInstance();
    
    protected JFrame frame;
    protected JPanel contentPanel;

    public Window(JFrame frame) {
        this.frame = frame;
        contentPanel = new JPanel();
        frame.setContentPane(contentPanel);
    }

    public void changeWindow(){
        switch(game.getGameState()){
            case GameState.PLAY -> {
                GameFrame gameFrame = new GameFrame(frame);
                gameFrame.refresh();
            }
            case GameState.EXIT -> {
                frame.dispose();
            }
                
            case GameState.MENU -> {
                MainMenu mainMenu = new MainMenu(frame);
                mainMenu.refresh();
            }
                
            case GameState.MENU_OPTIONS -> {
                OptionsPanel optionsPanel = new OptionsPanel(frame);
                optionsPanel.refresh();
            }
            
            case GameState.MENU_LEADERBOARD -> {
                LeaderBoard leaderBoard = new LeaderBoard(frame);
                leaderBoard.refresh();
            }
        }
    }
    
    public void display(){
        frame = new JFrame("App Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
    
    public abstract void refresh();

    protected void setPanel(JPanel panel) {
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }
}
