package dsw.rumap.app.gui.swing.controller.mapactions;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.controller.AbstractRumapActions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractRumapActions {

    public UndoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/move.png"));
        putValue(NAME, "MoveElement");
        putValue(SHORT_DESCRIPTION, "Move Element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AppCore.getInstance().getMapRepository().getCommandManager().undoCommand();
    }
}
