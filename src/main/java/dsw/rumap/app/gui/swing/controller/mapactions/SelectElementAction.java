package dsw.rumap.app.gui.swing.controller.mapactions;

import dsw.rumap.app.gui.swing.controller.AbstractRumapActions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SelectElementAction extends AbstractRumapActions {

    public SelectElementAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "SelectElement");
        putValue(SHORT_DESCRIPTION, "Select Element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
