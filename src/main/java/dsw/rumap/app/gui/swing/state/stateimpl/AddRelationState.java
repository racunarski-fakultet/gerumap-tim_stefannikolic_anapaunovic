package dsw.rumap.app.gui.swing.state.stateimpl;

import dsw.rumap.app.gui.swing.state.State;
import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.gui.swing.view.painters.ElementPainter;
import dsw.rumap.app.gui.swing.view.painters.RelationPainter;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.elements.Pair;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;
import dsw.rumap.app.maprepository.implementation.elements.RelationElement;

import java.awt.*;

public class AddRelationState implements State {

    private ElementPainter currentEP;

    @Override
    public void execute() {

    }

    @Override
    public void stateMousePressed(int x, int y, MindMapView mindMapView) {
        MindMap mindMap = mindMapView.getModel();

        x -= mindMapView.getTranslate().getFirst();
        y -= mindMapView.getTranslate().getSecond();

        x /= mindMapView.getPcScale()*mindMapView.getScale();
        y /= mindMapView.getPcScale()*mindMapView.getScale();

        for (ElementPainter ep :
                mindMapView.getPainters()) {
            if(ep.elementAt(new Point(x, y))) {
                Element tmpElement = new RelationElement(mindMap, ep.getElement(), x, y);
                ElementPainter painter = new RelationPainter(tmpElement);
                mindMapView.addPainter(painter);
                currentEP = painter;
                break;
            }
        }
    }

    @Override
    public void stateMouseDragged(int x, int y, MindMapView mindMapView) {

        x -= mindMapView.getTranslate().getFirst();
        y -= mindMapView.getTranslate().getSecond();

        x /= mindMapView.getPcScale()*mindMapView.getScale();
        y /= mindMapView.getPcScale()*mindMapView.getScale();

        if(currentEP == null) return;
        ((RelationElement) currentEP.getElement()).setEnd(new Pair<>(x, y));
    }

    @Override
    public void stateMouseReleased(int x, int y, MindMapView mindMapView) {

        x -= mindMapView.getTranslate().getFirst();
        y -= mindMapView.getTranslate().getSecond();

        x /= mindMapView.getPcScale()*mindMapView.getScale();
        y /= mindMapView.getPcScale()*mindMapView.getScale();

        if(currentEP == null) return;
        boolean found = false;
        for (ElementPainter ep :
                mindMapView.getPainters()) {
            if(ep.elementAt(new Point(x, y))) {
                ((RelationElement) currentEP.getElement()).setEnd(new Pair<>(x, y));
                ((RelationElement) currentEP.getElement()).setToTerm((TermElement) ep.getElement());
                found = true;
                break;
            }
        }

        if(!found || ((RelationElement) currentEP.getElement()).getFromTerm() == ((RelationElement) currentEP.getElement()).getToTerm()){
            mindMapView.removePainter(currentEP);
            mindMapView.getModel().delete(currentEP.getElement());
        }
        currentEP = null;
    }

}
