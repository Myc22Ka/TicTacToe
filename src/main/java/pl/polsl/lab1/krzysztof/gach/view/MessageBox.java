package pl.polsl.lab1.krzysztof.gach.view;

import java.awt.GridLayout;
import java.util.ArrayList;
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
 * @param <T> The type of the message content.
 * 
 * @author Krzysztof Gach
 * @version 1.0
 */
@RequiredArgsConstructor
public class MessageBox<T> {
    private final JFrame frame; // The frame to associate the dialogs with

    /**
     * Displays an information message dialog.
     *
     * @param message The message to display.
     * @param title The title of the dialog.
     */
    public void showInfoMessage(T message, String title) {
        JOptionPane.showMessageDialog(frame, message.toString(), title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Displays a warning message dialog.
     *
     * @param message The message to display.
     * @param title The title of the dialog.
     */
    public void showWarningMessage(T message, String title) {
        JOptionPane.showMessageDialog(
            frame, 
            message.toString(), 
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
    public void showErrorMessage(T message, String title) {
        JOptionPane.showMessageDialog(
            frame, 
            message.toString(), 
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
    public boolean showConfirmDialog(T message, String title) {
        int result = JOptionPane.showConfirmDialog(frame, message.toString(), title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

    /**
     * Displays a dialog that prompts the user for a single line of input.
     *
     * @param message The message to display.
     * @param title The title of the dialog.
     * @return The user input as a String, or null if the dialog is cancelled.
     */
    public String showInputDialog(T message, String title) {
        return JOptionPane.showInputDialog(frame, message.toString(), title, JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * Displays a dialog that prompts the user for two lines of input.
     *
     * @param messages
     * @return An array of Strings containing the user inputs, or null if the dialog is cancelled.
     */
    public List<String> showInputDialogList(String... messages) {
        JPanel panel = new JPanel(new GridLayout(messages.length, 2, 5, 5));

        JTextField[] fields = new JTextField[messages.length];

        for (int i = 0; i < messages.length; i++) {
            JLabel label = new JLabel(messages[i]);
            JTextField field = new JTextField(10);
            fields[i] = field;

            panel.add(label);
            panel.add(field);
        }

        int result = JOptionPane.showConfirmDialog(frame, panel, "Input Dialog", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            var inputValues = new ArrayList<String>();
            for (JTextField field : fields) {
                inputValues.add(field.getText());
            }
            return inputValues;
        }

    return null;
    }
}
