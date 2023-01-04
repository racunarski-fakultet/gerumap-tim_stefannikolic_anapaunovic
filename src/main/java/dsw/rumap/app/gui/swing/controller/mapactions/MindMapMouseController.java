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
            MainFrame.getInstance().getProjectView().medMousePressed(mindMapView.correctMouseX(e.getX()), mindMapView.correctMouseY(e.getY()), mindMapView);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MainFrame.getInstance().getProjectView().medMouseDragged(mindMapView.correctMouseX(e.getX()), mindMapView.correctMouseY(e.getY()), mindMapView);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MainFrame.getInstance().getProjectView().medMouseReleased(mindMapView.correctMouseX(e.getX()), mindMapView.correctMouseY(e.getY()), mindMapView);
    }
}
