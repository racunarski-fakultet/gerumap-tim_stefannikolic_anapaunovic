package dsw.rumap.app.gui.swing.controller.mapactions;

import dsw.rumap.app.gui.swing.controller.AbstractRumapActions;
import dsw.rumap.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class CentralizeTermAction extends AbstractRumapActions {

    public CentralizeTermAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/centralized.png"));
        putValue(NAME, "CentralizeTerm");
        putValue(SHORT_DESCRIPTION, "Centralize Term");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().getCurrentMindMapView().centralizeTerm();

    }
}
