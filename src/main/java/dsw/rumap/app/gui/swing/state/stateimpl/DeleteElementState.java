package dsw.rumap.app.gui.swing.state.stateimpl;

import dsw.rumap.app.gui.swing.state.State;
import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.gui.swing.view.painters.ElementPainter;

import java.awt.*;

public class DeleteElementState implements State {
    @Override
    public void execute() {

    }

    @Override
    public void stateMousePressed(int x, int y, MindMapView mapView) {

        Point pos = new Point(x,y);
        ElementPainter p = null;
        //i sve njegove veze treba da se obrisu

        for(ElementPainter painter: mapView.getPainters()){
            if(painter.elementAt(pos)){
                p = painter;
                break;
            }
        }

        if(p != null){
            mapView.getModel().delete(p.getElement());
            mapView.getPainters().remove(p);

        }

    }


    @Override
    public void stateMouseReleased() {

    }

    @Override
    public void stateMouseDragged() {

    }
}
