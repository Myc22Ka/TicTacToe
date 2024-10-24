package pl.polsl.lab1.krzysztof.gach.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author kris
 */
public class ExitButton extends ButtonAction {
    public ExitButton() {
        super("Exit");
    }

    @Override
    protected ActionListener createActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // to change
            }
        };
    }
}
