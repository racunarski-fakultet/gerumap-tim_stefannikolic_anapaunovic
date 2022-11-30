package dsw.rumap.app.gui.swing.controller.mapactions;

import dsw.rumap.app.gui.swing.controller.AbstractRumapActions;
import dsw.rumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteElementAction extends AbstractRumapActions {

    public DeleteElementAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "DeleteElement");
        putValue(SHORT_DESCRIPTION, "Delete Element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startDeleteElementState();
    }
}
