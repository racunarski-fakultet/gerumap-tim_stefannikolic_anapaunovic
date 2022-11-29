package dsw.rumap.app.gui.swing.controller.mapactions;

import dsw.rumap.app.gui.swing.controller.AbstractRumapActions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddRelationAction extends AbstractRumapActions {

    public AddRelationAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "AddReletion");
        putValue(SHORT_DESCRIPTION, "New Relation");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
