package pl.polsl.lab1.krzysztof.gach.view;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MessageBox {

    private final JFrame frame;

    public MessageBox(JFrame frame) {
        this.frame = frame;
    }

    // Information Message
    public void showInfoMessage(String message, String title) {
        JOptionPane.showMessageDialog(
            frame, 
            message, 
            title, 
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Warning Message
    public void showWarningMessage(String message, String title) {
        JOptionPane.showMessageDialog(
            frame, 
            message, 
            title, 
            JOptionPane.WARNING_MESSAGE
        );
    }

    // Error Message
    public void showErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(
            frame, 
            message, 
            title, 
            JOptionPane.ERROR_MESSAGE
        );
    }

    // Confirmation Dialog
    public boolean showConfirmDialog(String message, String title) {
        int result = JOptionPane.showConfirmDialog(
            frame, 
            message, 
            title, 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );
        return result == JOptionPane.YES_OPTION;
    }

    // Custom Input Dialog (Text Field)
    public String showInputDialog(String message, String title) {
        return JOptionPane.showInputDialog(
            frame, 
            message, 
            title, 
            JOptionPane.PLAIN_MESSAGE
        );
    }
    
    public String[] showTwoInputDialog(String message1, String message2, String title) {
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
            return new String[] { field1.getText(), field2.getText() };
        }
        
        return null;
    }
}
