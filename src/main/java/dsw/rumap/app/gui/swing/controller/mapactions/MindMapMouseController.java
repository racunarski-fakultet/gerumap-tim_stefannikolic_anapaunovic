package dsw.rumap.app.gui.swing.controller.mapactions;

import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.gui.swing.view.MindMapView;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;

public class MindMapMouseController extends MouseAdapter {

    private MindMapView mindMapView;

    public MindMapMouseController(MindMapView mindMapView) {
        this.mindMapView = mindMapView;
    }

    public void mousePressed(MouseEvent e) {

        if (e.getButton()==MouseEvent.BUTTON1){
            MainFrame.getInstance().getProjectView().medMousePressed(e.getX(), e.getY(), mindMapView);
            //MainFrame.getInstance().getProjectView().executeRequest();
        }
    }
}
