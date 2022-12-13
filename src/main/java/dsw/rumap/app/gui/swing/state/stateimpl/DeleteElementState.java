package dsw.rumap.app.gui.swing.state.stateimpl;

import dsw.rumap.app.gui.swing.state.State;
import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.gui.swing.view.painters.ElementPainter;
import dsw.rumap.app.gui.swing.view.painters.RelationPainter;
import dsw.rumap.app.maprepository.implementation.elements.RelationElement;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteElementState implements State {
    @Override
    public void execute() {

    }

    @Override
    public void stateMousePressed(int x, int y, MindMapView mindMapView) {
        ElementPainter currentPainter = null;
        List<ElementPainter> toRemove = new ArrayList<>();
        for (ElementPainter ep :
                mindMapView.getPainters()) {
            if(ep.elementAt(new Point(x, y))) {
                currentPainter = ep;
                break;
            }
        }

        if(currentPainter == null)  return;

        for (ElementPainter rep :
                mindMapView.getPainters()) {
            if(rep instanceof RelationPainter){
                RelationElement re = (RelationElement) rep.getElement();
                if(re.getFromTerm() == currentPainter.getElement() || re.getToTerm() == currentPainter.getElement()) {
                    toRemove.add(rep);
                }
            }
        }

        for (ElementPainter rep :
                toRemove) {
            mindMapView.removePainter(rep);
            mindMapView.getModel().delete(rep.getElement());
        }

        mindMapView.getMapSelectionModel().unselect(currentPainter.getElement());
        mindMapView.removePainter(currentPainter);
        mindMapView.getModel().delete(currentPainter.getElement());
    }


    @Override
    public void stateMouseReleased(int x, int y, MindMapView mindMapView) {

    }

    @Override
    public void stateMouseDragged(int x, int y, MindMapView mindMapView) {

    }
}
