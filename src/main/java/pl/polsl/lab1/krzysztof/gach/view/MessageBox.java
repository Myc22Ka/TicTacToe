package pl.polsl.lab1.krzysztof.gach.view;

import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import lombok.RequiredArgsConstructor;

/**
 * The MessageBox class provides utility methods for displaying various types of message dialogs,
 * including information messages, warning messages, error messages, confirmation dialogs, and input dialogs.
 * 
 * @author Krzysztof Gach
 * @version 1.0
 */

@RequiredArgsConstructor
public class MessageBox {
    private final JFrame frame; // The frame to associate the dialogs with

    /**
     * Displays an information message dialog.
     *
     * @param message The message to display.
     * @param title The title of the dialog.
     */
    public void showInfoMessage(String message, String title) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays a warning message dialog.
     *
     * @param message The message to display.
     * @param title The title of the dialog.
     */
    public void showWarningMessage(String message, String title) {
        JOptionPane.showMessageDialog(
            frame, 
            message, 
            title, 
            JOptionPane.WARNING_MESSAGE
        );
    }

    /**
     * Displays an error message dialog.
     *
     * @param message The message to display.
     * @param title The title of the dialog.
     */
    public void showErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(
            frame, 
            message, 
            title, 
            JOptionPane.ERROR_MESSAGE
        );
    }

    /**
     * Displays a confirmation dialog with Yes and No options.
     *
     * @param message The message to display.
     * @param title The title of the dialog.
     * @return true if the user selects "Yes", false otherwise.
     */
    public boolean showConfirmDialog(String message, String title) {
        int result = JOptionPane.showConfirmDialog(frame, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

    /**
     * Displays a dialog that prompts the user for a single line of input.
     *
     * @param message The message to display.
     * @param title The title of the dialog.
     * @return The user input as a String, or null if the dialog is cancelled.
     */
    public String showInputDialog(String message, String title) {
        return JOptionPane.showInputDialog(frame, message, title, JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * Displays a dialog that prompts the user for two lines of input.
     *
     * @param message1 The message for the first input field.
     * @param message2 The message for the second input field.
     * @param title The title of the dialog.
     * @return An array of Strings containing the user inputs, or null if the dialog is cancelled.
     */
    public List<String> showTwoInputDialog(String message1, String message2, String title) {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
    
        JLabel label1 = new JLabel(message1);
        JTextField field1 = new JTextField(10);
    
        JLabel label2 = new JLabel(message2);
        JTextField field2 = new JTextField(10);
    
        panel.add(label1);
        panel.add(field1);
        panel.add(label2);
        panel.add(field2);

        int result = JOptionPane.showConfirmDialog(frame, panel, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
        if (result == JOptionPane.OK_OPTION) {
            return Arrays.asList(field1.getText(), field2.getText());
        }
    
        return null;
    }
}
