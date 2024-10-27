package pl.polsl.lab1.krzysztof.gach.view;

import javax.swing.*;
import java.awt.*;

public class LabeledTextField extends JPanel {
    private final JLabel label;
    private final JTextField textField;

    public LabeledTextField(String labelText, int textFieldColumns) {
        setLayout(new BorderLayout(5, 0));

        label = new JLabel(labelText);
        textField = new JTextField(textFieldColumns);

        add(label, BorderLayout.WEST);
        add(textField, BorderLayout.CENTER);
    }

    public String getText() {
        return textField.getText();
    }

    public void setText(String text) {
        textField.setText(text);
    }

    public JTextField getTextField() {
        return textField;
    }

    public JLabel getLabel() {
        return label;
    }
}
