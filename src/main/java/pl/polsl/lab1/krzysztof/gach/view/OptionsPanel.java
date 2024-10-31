package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import pl.polsl.lab1.krzysztof.gach.controller.Game;
import pl.polsl.lab1.krzysztof.gach.controller.Validator;
import pl.polsl.lab1.krzysztof.gach.model.AudioManager;

public class OptionsPanel extends Window {
    private final Game game = Game.getInstance();
    private final AudioManager audioManager = AudioManager.getInstance();
    
    private String selectedResolution;
    private boolean isFullScreen = false;
    private int volume = -10;
    private final LabeledTextField[] playerFields = new LabeledTextField[2];
    private final LabeledTextField[] symbolFields = new LabeledTextField[2];
    private LabeledTextField boardSize;

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
        
        int minVolume = -80;
        int maxVolume = 0;
        int initialVolume = (int) volume;
        
        JSlider volumeSlider = new JSlider(minVolume, maxVolume, initialVolume);
        volumeSlider.setMajorTickSpacing(10);
        volumeSlider.setSnapToTicks(true);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        
        volumeSlider.setToolTipText("Set up game volume");
        volumeSlider.getAccessibleContext().setAccessibleDescription("Slider to set up game volume");
        
        volumeSlider.addMouseListener(new MouseAdapter() {
        @Override
            public void mouseReleased(MouseEvent e) {
                int sliderValue = volumeSlider.getValue();
                int snapValue = (Math.round(sliderValue / 5.0f) * 5);
                volumeSlider.setValue(snapValue);
            }
        });
        
        volumeSlider.addChangeListener(e -> {
            volume = volumeSlider.getValue();
        });

        audioPanel.add(volumeLabel);
        audioPanel.add(volumeSlider);

        return audioPanel;
    }

    private JPanel createAppearanceSettings() {
        var appearancePanel = createSection("Appearance Settings"); 
        
        createPlayerFiled(appearancePanel, 0);
        createPlayerFiled(appearancePanel, 1);
        
        boardSize = new LabeledTextField("Board size: ", 3);
        boardSize.setText("" + game.getBoard().size());
        
        boardSize.setToolTipText("Set up size of the game board size");
        boardSize.getAccessibleContext().setAccessibleDescription("TextFiled to set up game board size");
        
        appearancePanel.add(boardSize);

        return appearancePanel;
    }
    
    private void createPlayerFiled(JPanel appearancePanel, int index){
        playerFields[index] = new LabeledTextField("Player " + (index + 1) + " Name:", 15);
        symbolFields[index] = new LabeledTextField("Player " + (index + 1) + " Symbol:", 15);
        
        playerFields[index].setToolTipText("Set up player " + (index + 1) + " name");
        playerFields[index].getAccessibleContext().setAccessibleDescription("TextFiled to set up player " + (index + 1) + " name");
        
        symbolFields[index].setToolTipText("Set up player " + (index + 1) + " symbol");
        symbolFields[index].getAccessibleContext().setAccessibleDescription("TextFiled to set up player " + (index + 1) + " symbol");
        
        appearancePanel.add(playerFields[index]);
        appearancePanel.add(symbolFields[index]);
    }

    private JPanel createDisplaySettings() {
        var displayPanel = createSection("Display Settings");

        JLabel resolutionLabel = new JLabel("Resolution:");
        String[] resolutions = {"800x600", "1024x768", "1280x720", "1920x1080"};
        JComboBox<String> resolutionList = new JComboBox<>(resolutions);
        resolutionList.setSelectedIndex(0);
        resolutionList.addActionListener(e -> selectedResolution = resolutionList.getSelectedItem().toString());
        
        resolutionList.setToolTipText("Set up the game resolution");
        resolutionList.getAccessibleContext().setAccessibleDescription("ComboBox to set up game resolution");

        JCheckBox fullScreenCheckBox = new JCheckBox("Full Screen", isFullScreen);
        fullScreenCheckBox.addActionListener((ActionEvent e) -> {
            isFullScreen = fullScreenCheckBox.isSelected();
            resolutionList.setEnabled(!isFullScreen);
        });
        
        fullScreenCheckBox.setToolTipText("Set up full screen mode");
        fullScreenCheckBox.getAccessibleContext().setAccessibleDescription("CheckBox to set up full screen mode");

        displayPanel.add(resolutionLabel);
        displayPanel.add(resolutionList);
        displayPanel.add(fullScreenCheckBox);

        return displayPanel;
    }

    private void saveOptions() {
        String[] dimensions = selectedResolution.split("x");
        frame.setSize(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));

        frame.setVisible(false);
        frame.dispose();
        
        audioManager.setMasterVolume(volume);

        frame.setUndecorated(isFullScreen);
        frame.setExtendedState(JFrame.NORMAL);
    
        if (isFullScreen) frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setVisible(true);
        
        boolean playersCreated = createPlayers();
        boolean validSize = changeSize();

        if (playersCreated && validSize) {
            JOptionPane.showMessageDialog(frame, "Settings saved and players created successfully.");
        } else {
            JOptionPane.showMessageDialog(frame, "Settings saved, but there were issues creating players.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private boolean changeSize(){
        boolean status = true;
        
        var validator = new Validator();
        var messageBox = new MessageBox(frame);
            
        if(!boardSize.getText().isEmpty()) validator.setValidSize(boardSize.getText(), messageBox);
        
        return status;
    }
    
    private boolean createPlayers() {
        boolean allCreated = true;
        var validator = new Validator();
        var messageBox = new MessageBox(frame);
        
        for (int i = 0; i < playerFields.length; i++) {
            String name = playerFields[i].getText().trim();
            String symbol = symbolFields[i].getText().trim();
            
            if(!name.isEmpty() && symbol.isEmpty()) validator.setValidPlayer(name, symbol, messageBox);
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
