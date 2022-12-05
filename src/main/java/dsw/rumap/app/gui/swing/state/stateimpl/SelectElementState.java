package dsw.rumap.app.gui.swing.state.stateimpl;

import dsw.rumap.app.gui.swing.state.State;
import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.gui.swing.view.painters.ElementPainter;
import dsw.rumap.app.maprepository.implementation.elements.MapSelectionModel;

import java.awt.*;

public class SelectElementState implements State {
    @Override
    public void execute() {

    }

    @Override
    public void stateMousePressed(int x, int y, MindMapView mindMapView) {
        MapSelectionModel selectedElements = mindMapView.getSelectedElements();
        for (ElementPainter ep :
                mindMapView.getPainters()) {
            if(ep.elementAt(new Point(x, y))) {
                if(selectedElements.isSelected(ep.getElement()))
                    selectedElements.unselect(ep.getElement());
                else selectedElements.select(ep.getElement());
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
