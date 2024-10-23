package pl.polsl.lab1.krzysztof.gach.view;

import java.awt.event.*;
import javax.swing.*;

public abstract class Window extends JFrame {
    private JPanel contentPanel;
    
    public Window() {
        setTitle("TicTacToe");
        setupCommonWindow();
        contentPanel = new JPanel();
        add(contentPanel);
        setupWindow();
        setupCloseShortcut();
    }
    
    private void setupCommonWindow() {
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    protected abstract void setupWindow();
    
    protected abstract JPanel getContentPanel();

    // Common method for setting up a close shortcut (Ctrl+Q)
    private void setupCloseShortcut() {
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        KeyStroke closeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK);
        inputMap.put(closeKeyStroke, "closeApplication");
        actionMap.put("closeApplication", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void displayWindow() {
        setVisible(true);
    }

    public void openWindow(Window window) {
        window.displayWindow();
    }
    
    public void updateContent(JComponent newContent) {
        contentPanel.removeAll();
        contentPanel.add(newContent);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    protected Window getWindow() {
        return this;
    }
}
