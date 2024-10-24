package pl.polsl.lab1.krzysztof.gach.view.buttons;

import java.awt.event.ActionListener;
import javax.swing.JButton;
/**
 *
 * @author Krzysztof Gach
 * @version 1.2
 */
public abstract class ButtonAction {
        protected JButton button;

        public ButtonAction(String title) {
            button = new JButton(title);
            button.addActionListener(createActionListener());
        }

        public JButton getButton() {
            return button;
        }

        protected abstract ActionListener createActionListener();
    }