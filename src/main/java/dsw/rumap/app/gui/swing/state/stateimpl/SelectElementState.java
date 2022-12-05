package dsw.rumap.app.gui.swing.state.stateimpl;

import dsw.rumap.app.gui.swing.state.State;
import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.gui.swing.view.painters.ElementPainter;

import java.awt.*;

public class SelectElementState implements State {
    @Override
    public void execute() {

    }

    @Override
    public void stateMousePressed(int x, int y, MindMapView mapView) {
        Point pos = new Point(x,y);
        ElementPainter p = null;

        for(ElementPainter painter: mapView.getPainters()){
            if(painter.elementAt(pos)){
                p = painter;
                break;
            }
        }
        if(p != null){

           mapView.getSelected().add(p.getElement());
           //kada se unselectuje?? tad treba i da se obrise
            //kad sam u select stateu kliknem na element, ako se nalazi u listi selektovanih unselectujem
            //ga, ako se ne nalazi dodajem ga u listi selektovnih
        }
    }


    @Override
    public void stateMouseReleased() {

    }

    @Override
    public void stateMouseDragged() {

    }
}
