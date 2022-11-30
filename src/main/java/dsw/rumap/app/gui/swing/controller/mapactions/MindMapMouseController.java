package dsw.rumap.app.gui.swing.controller.mapactions;

import dsw.rumap.app.gui.swing.view.MainFrame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;

public class MindMapMouseController extends MouseAdapter {

    public void mousePressed(MouseEvent e) {

        if (e.getButton()==MouseEvent.BUTTON1){
            MainFrame.getInstance().getProjectView().executeRequest();
        }
    }
}
