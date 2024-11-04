package pl.polsl.lab1.krzysztof.gach.view;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import java.awt.*;

/**
 * A custom panel that combines a JLabel and a JTextField for a labeled text input field.
 * 
 * @author Krzysztof Gach
 * @version 1.0
 */
@Getter
@RequiredArgsConstructor
public class LabeledTextField extends JPanel {
    /**
     * Label for the text field
     */
    private final JLabel label;

    /**
     * Text field for user input
     */
    private final JTextField textField;

    /**
     * Constructs a LabeledTextField with specified label text and text field columns.
     *
     * @param labelText the text to display on the label
     * @param textFieldColumns the number of columns for the text field
     */
    public LabeledTextField(String labelText, int textFieldColumns) {
        setLayout(new BorderLayout(5, 0));

        label = new JLabel(labelText);
        textField = new JTextField(textFieldColumns);

        add(label, BorderLayout.WEST);
        add(textField, BorderLayout.CENTER);
    }

    /**
     * Returns the current text from the text field.
     *
     * @return the text currently in the text field
     */
    public String getText() {
        return textField.getText();
    }

    /**
     * Sets the text in the text field.
     *
     * @param text the text to set in the text field
     */
    public void setText(String text) {
        textField.setText(text);
    }
}