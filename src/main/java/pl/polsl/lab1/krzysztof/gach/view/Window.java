package pl.polsl.lab1.krzysztof.gach.view;

import java.awt.event.*;
import javax.swing.*;

public abstract class Window {
    protected JFrame frame;
    protected JPanel contentPanel;

    public Window(JFrame frame) {
        this.frame = frame;
        contentPanel = new JPanel();
        frame.setContentPane(contentPanel);
    }

    public abstract void refresh();

    protected void setPanel(JPanel panel) {
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }
}
