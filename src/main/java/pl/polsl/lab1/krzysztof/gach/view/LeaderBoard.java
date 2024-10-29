package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LeaderBoard extends Window {

    public LeaderBoard(JFrame frame) {
        super(frame);

        contentPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("LeaderBoard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(50, 50, 50));
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.add(createTable());
        contentPanel.add(optionsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(new ButtonAction("Back to Menu", e -> goBackToMenu()).getButton());
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setPanel(contentPanel);
    }
    
    private JPanel createTable() {
        String[] columnNames = {"ID", "Player1", "Punkty1", "Player2", "Punkty2", "Runda"};
        Object[][] data = {
            {1, "Alice", 150, "Bob", 120, 5},
            {2, "Charlie", 200, "David", 180, 6},
            {3, "Eva", 100, "Frank", 140, 4}
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setForeground(new Color(30, 30, 30));
        table.setBackground(frame.getBackground());

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(220, 220, 220));
        table.getTableHeader().setForeground(new Color(30, 30, 30));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
        tablePanel.add(scrollPane);
        tablePanel.setBorder(BorderFactory.createTitledBorder("Match Results")); // Optional title for the table

        return tablePanel;
    }

    @Override
    public void refresh() {
        setPanel(contentPanel);
    }

    private void goBackToMenu() {
        MainMenu menu = new MainMenu(frame);
        menu.refresh();
    }
}
