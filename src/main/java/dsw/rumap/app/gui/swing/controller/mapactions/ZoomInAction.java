package dsw.rumap.app.gui.swing.controller.mapactions;

import dsw.rumap.app.gui.swing.controller.AbstractRumapActions;
import dsw.rumap.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ZoomInAction extends AbstractRumapActions {

    public ZoomInAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(InputEvent., ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/zoom.png"));
        putValue(NAME, "ZoomIn");
        putValue(SHORT_DESCRIPTION, "Zoom In");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getProjectView() == null || MainFrame.getInstance().getProjectView().getCurrentMindMapView() == null)
            return;
        MainFrame.getInstance().getProjectView().getCurrentMindMapView().zoomIn();
    }
}
