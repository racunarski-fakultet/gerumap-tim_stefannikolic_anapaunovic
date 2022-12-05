package dsw.rumap.app.gui.swing.state.stateimpl;

import dsw.rumap.app.gui.swing.state.State;
import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.gui.swing.view.painters.ElementPainter;
import dsw.rumap.app.gui.swing.view.painters.TermPainter;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.elements.RelationElement;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;

import java.awt.*;

public class DeleteElementState implements State {
    @Override
    public void execute() {

    }

    @Override
    public void stateMousePressed(int x, int y, MindMapView mindMapView) {
        for (ElementPainter ep :
                mindMapView.getPainters()) {
            if(ep.elementAt(new Point(x, y))) {
                mindMapView.getSelectedElements().unselect(ep.getElement());
                mindMapView.removePainter(ep);
                mindMapView.getModel().delete(ep.getElement());
                break;
            }
        }
    }

    @Override
    public void stateMouseReleased(int x, int y, MindMapView mindMapView) {

    }

    @Override
    public void stateMouseDragged(int x, int y, MindMapView mindMapView) {

    }
}
