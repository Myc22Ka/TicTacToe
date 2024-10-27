package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.*;
import java.awt.*;
import pl.polsl.lab1.krzysztof.gach.controller.Game;
import pl.polsl.lab1.krzysztof.gach.model.InvalidNameException;
import pl.polsl.lab1.krzysztof.gach.model.Player;

public class OptionsPanel extends Window {
    private final Game game = Game.getInstance();
    
    private String selectedResolution;
    private boolean isFullScreen = false;
    private boolean isVSyncEnabled = false;
    private float volume = 0.5f;
    private boolean isDarkMode = false;
    private LabeledTextField[] playerFields = new LabeledTextField[2];

    public OptionsPanel(JFrame frame) {
        super(frame);

        selectedResolution = "800x600";

        contentPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Options", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        optionsPanel.add(createAudioSettings());
        optionsPanel.add(createAppearanceSettings());
        optionsPanel.add(createDisplaySettings());

        contentPanel.add(optionsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(new ButtonAction("Save", e -> saveOptions()).getButton());
        buttonPanel.add(new ButtonAction("Back to Menu", e -> goBackToMenu()).getButton());
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setPanel(contentPanel);
    }
    
    private JPanel createSection(String title){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder(title));
        
        return panel;
    }

    private JPanel createAudioSettings() {
        var audioPanel = createSection("Audio settings");

        JLabel volumeLabel = new JLabel("Volume:");
        JSlider volumeSlider = new JSlider(0, 100, (int) (volume * 100));
        volumeSlider.addChangeListener(e -> volume = volumeSlider.getValue() / 100.0f);

        audioPanel.add(volumeLabel);
        audioPanel.add(volumeSlider);

        return audioPanel;
    }

    private JPanel createAppearanceSettings() {
        var appearancePanel = createSection("Appearance Settings");

        JCheckBox themeCheckBox = new JCheckBox("Dark Mode", isDarkMode);
        themeCheckBox.addActionListener(e -> isDarkMode = themeCheckBox.isSelected());

        appearancePanel.add(themeCheckBox);
        
        playerFields[0] = new LabeledTextField("Player 1 Name:", 15);
        playerFields[1] = new LabeledTextField("Player 2 Name:", 15);

        for (LabeledTextField field : playerFields){
            appearancePanel.add(field);
        }

        return appearancePanel;
    }

    private JPanel createDisplaySettings() {
        var displayPanel = createSection("Display Settings");

        JLabel resolutionLabel = new JLabel("Resolution:");
        String[] resolutions = {"800x600", "1024x768", "1280x720", "1920x1080"};
        JComboBox<String> resolutionList = new JComboBox<>(resolutions);
        resolutionList.setSelectedIndex(0);
        resolutionList.addActionListener(e -> selectedResolution = resolutionList.getSelectedItem().toString());

        JCheckBox fullScreenCheckBox = new JCheckBox("Full Screen", isFullScreen);
        fullScreenCheckBox.addActionListener(e -> isFullScreen = fullScreenCheckBox.isSelected());

        JCheckBox vsyncCheckBox = new JCheckBox("Vertical Sync (V-Sync)", isVSyncEnabled);
        vsyncCheckBox.addActionListener(e -> isVSyncEnabled = vsyncCheckBox.isSelected());

        displayPanel.add(resolutionLabel);
        displayPanel.add(resolutionList);
        displayPanel.add(fullScreenCheckBox);
        displayPanel.add(vsyncCheckBox);

        return displayPanel;
    }

    private void saveOptions() {
        String[] dimensions = selectedResolution.split("x");
        frame.setSize(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));

        frame.setVisible(false);
        frame.dispose();

        frame.setUndecorated(isFullScreen);
        frame.setExtendedState(JFrame.NORMAL);
    
        if (isFullScreen) frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setVisible(true);
        
        boolean playersCreated = createPlayers();

        if (playersCreated) {
            JOptionPane.showMessageDialog(frame, "Settings saved and players created successfully.");
        } else {
            JOptionPane.showMessageDialog(frame, "Settings saved, but there were issues creating players.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private boolean createPlayers() {
        boolean allCreated = true;
        
        for (LabeledTextField field : playerFields) {
            String name = field.getText().trim();
            if (!name.isEmpty()) {
                try {
                    Player player = new Player(name);
                    player.checkName();
                    game.addPlayer(player);
                } catch (InvalidNameException e) {
                    allCreated = false;
                    JOptionPane.showMessageDialog(frame, e.getMessage(), "Invalid Name", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        
        return allCreated;
    }

    private void goBackToMenu() {
        MainMenu menu = new MainMenu(frame);
        menu.refresh();
    }

    @Override
    public void refresh() {
        setPanel(contentPanel);
    }
}
