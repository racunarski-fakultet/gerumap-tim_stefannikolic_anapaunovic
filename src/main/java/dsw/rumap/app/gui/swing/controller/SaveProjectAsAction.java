package dsw.rumap.app.gui.swing.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SaveProjectAsAction extends AbstractRumapActions{

    public SaveProjectAsAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/images/info.png"));
        putValue(NAME, "Save Project As");
        putValue(SHORT_DESCRIPTION, "Save Project As");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
