package dsw.rumap.app.gui.swing.state.stateimpl;

import dsw.rumap.app.gui.swing.state.State;
import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;

import java.util.List;

public class MoveElementState implements State {

    private int startX;
    private int startY;

    @Override
    public void execute() {

    }
    @Override
    public void stateMousePressed(int x, int y, MindMapView mindMapView) {
            startX = x;
            startY = y;
    }

    @Override
    public void stateMouseDragged(int x, int y, MindMapView mindMapView) {
        int moveX = x - startX;
        int moveY = y - startY;

        for(Element selected: mindMapView.getMapSelectionModel().getSelected()){
            if(selected instanceof TermElement) {
                TermElement selectedTerm = (TermElement) selected;
                selectedTerm.setPosition(selectedTerm.getPosition().getFirst() + moveX, selectedTerm.getPosition().getSecond() + moveY);
            }
            startX = x;
            startY = y;
        }
    }

    @Override
    public void stateMouseReleased(int x, int y, MindMapView mindMapView) {}
}