package dsw.rumap.app.gui.swing.controller;

import dsw.rumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.Stack;

public class InfoAction extends AbstractRumapActions {

    public InfoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/info.png"));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Icon ico = loadIcon("/images/stefan.jpeg");
        JOptionPane.showMessageDialog(MainFrame.getInstance(), "Stefan Nikolic 127/21RN\nAna Paunovic 110/20RN", "Info", JOptionPane.INFORMATION_MESSAGE, ico);

    }
}
