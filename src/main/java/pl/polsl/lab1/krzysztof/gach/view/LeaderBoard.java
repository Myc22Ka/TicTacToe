package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The LeaderBoard class displays a leaderboard of player scores in a table format.
 * 
 * @author Krzysztof Gach
 * @version 1.0
 */
public class LeaderBoard extends Window {

    /**
     * Constructs a new LeaderBoard instance.
     *
     * @param frame the parent JFrame for this window
     */
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
        
        ButtonAction backButton = new ButtonAction("Back to Menu", e -> goBackToMenu());
        buttonPanel.add(backButton.getButton());   
        
        addShortcuts(backButton.getButton());
        
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setPanel(contentPanel);
    }
    
    /**
     * Adds keyboard shortcuts for the back button.
     *
     * @param backButton the back button to set shortcuts for
     */
    private void addShortcuts(JButton backButton) {
        // Key bindings for the Back to Menu button (Ctrl + B)
        InputMap inputMap = backButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = backButton.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("control B"), "backAction");
        actionMap.put("backAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackToMenu();
            }
        });
    }
    
    /**
     * Creates a table to display match results.
     *
     * @return a JPanel containing the table with match results
     */
    private JPanel createTable() {
        List<String> columnNames = Arrays.asList("ID", "Player1", "Punkty1", "Player2", "Punkty2", "Runda");
        
        var dataList = new ArrayList<List<Object>>();
        
        dataList.add(Arrays.asList(1, "Alice", 150, "Bob", 120, 5));
        dataList.add(Arrays.asList(2, "Charlie", 200, "David", 180, 6));
        dataList.add(Arrays.asList(3, "Eva", 100, "Frank", 140, 4));
        dataList.add(Arrays.asList(4, "George", 170, "Hannah", 160, 7));
        dataList.add(Arrays.asList(5, "Ian", 90, "Jane", 110, 3));
        dataList.add(Arrays.asList(6, "Kyle", 130, "Lily", 150, 5));
        dataList.add(Arrays.asList(7, "Mike", 120, "Nina", 140, 6));
        dataList.add(Arrays.asList(8, "Oliver", 160, "Paula", 200, 8));
        dataList.add(Arrays.asList(9, "Quinn", 175, "Ray", 135, 5));
        dataList.add(Arrays.asList(10, "Sophia", 130, "Tom", 155, 7));
        dataList.add(Arrays.asList(11, "Uma", 110, "Victor", 145, 4));
        dataList.add(Arrays.asList(12, "Wendy", 190, "Xander", 120, 6));
        dataList.add(Arrays.asList(13, "Yara", 80, "Zane", 100, 2));
        dataList.add(Arrays.asList(14, "Alex", 165, "Bella", 130, 5));
        dataList.add(Arrays.asList(15, "Cody", 140, "Daisy", 115, 3));
        dataList.add(Arrays.asList(16, "Ella", 115, "Finn", 125, 4));
        dataList.add(Arrays.asList(17, "Gina", 150, "Hugo", 130, 6));
        dataList.add(Arrays.asList(18, "Ivy", 125, "Jack", 110, 4));
        dataList.add(Arrays.asList(19, "Kara", 95, "Leo", 85, 2));
        dataList.add(Arrays.asList(20, "Mia", 130, "Noah", 145, 7));    

        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    
        for (String columnName : columnNames) {
            tableModel.addColumn(columnName);
        }

        for (List<Object> rowData : dataList) {
            tableModel.addRow(rowData.toArray());
        }
        
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
        tablePanel.setBorder(BorderFactory.createTitledBorder("Match Results"));

        return tablePanel;
    }

    @Override
    public void refresh() {
        setPanel(contentPanel);
    }

    /**
     * Navigates back to the main menu.
     */
    private void goBackToMenu() {
        MainMenu menu = new MainMenu(frame);
        menu.refresh();
    }
}
