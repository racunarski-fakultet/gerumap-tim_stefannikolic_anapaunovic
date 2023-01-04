package dsw.rumap.app.gui.swing.controller.mapactions;

import dsw.rumap.app.gui.swing.controller.AbstractRumapActions;
import dsw.rumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SelectElementAction extends AbstractRumapActions {

    public SelectElementAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/selection.png"));
        putValue(NAME, "SelectElement");
        putValue(SHORT_DESCRIPTION, "Select Element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startSelectElementState();
    }
}
