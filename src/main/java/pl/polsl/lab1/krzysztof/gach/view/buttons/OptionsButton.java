package pl.polsl.lab1.krzysztof.gach.view.buttons;

import java.awt.event.*;

/**
 *
 * @author Krzysztof Gach
 * @version 1.1
 */
public class OptionsButton extends ButtonAction {
    public OptionsButton() {
        super("Options");
    }

    @Override
    protected ActionListener createActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Options clicked");
            }
        };
    }
}
