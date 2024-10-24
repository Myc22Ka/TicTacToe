/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab1.krzysztof.gach.view.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author kris
 */
public class ContinueButton extends ButtonAction {
    public ContinueButton() {
        super("Continue");
    }

    @Override
    protected ActionListener createActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Continue game clicked");
            }
        };
    }
}
