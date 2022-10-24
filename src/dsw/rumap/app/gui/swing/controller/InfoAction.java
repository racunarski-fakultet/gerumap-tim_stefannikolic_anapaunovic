package dsw.rumap.app.gui.swing.controller;

import dsw.rumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class InfoAction extends AbstractRumapActions {

    public InfoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(MainFrame.getInstance(), "Stefan i Ana", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
