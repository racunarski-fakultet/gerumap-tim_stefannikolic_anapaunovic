package dsw.rumap.app.gui.swing.state.stateimpl;

import dsw.rumap.app.gui.swing.state.State;
import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.gui.swing.view.painters.ElementPainter;
import dsw.rumap.app.maprepository.implementation.elements.MapSelectionModel;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SelectElementState implements State {

    private Rectangle2D selectionRec;
    private int x1;
    private int y1;
    private boolean found = false;
    @Override
    public void execute() {

    }

    @Override
    public void stateMousePressed(int x, int y, MindMapView mindMapView, int clickCount) {
        MapSelectionModel selectedElements = mindMapView.getSelectedElements();
        found = false;
        for (ElementPainter ep :
                mindMapView.getPainters()) {
            if(ep.elementAt(new Point(x, y))) {
                found = true;
                if(!selectedElements.isSelected(ep.getElement())){
                    selectedElements.select(ep.getElement());
                }
                else selectedElements.unselect(ep.getElement());
                break;
            }
        }
        if(found == false){
            selectionRec = mindMapView.getSelectionR(x,y,0,0);
            selectedElements.clear();
            this.x1 = x;
            this.y1 = y;
        }
    }

    @Override
    public void stateMouseReleased(int x, int y, MindMapView mindMapView) {
        //if(mindMapView.getSelection() != null)
          //  mindMapView.setLastCoordinates((int) mindMapView.getSelection().getX(), (int) mindMapView.getSelection().getY());
        mindMapView.setCoordinates(0,0,0,0);//?
    }

    @Override
    public void stateMouseDragged(int x, int y, MindMapView mindMapView) {
        if (found == true)
            return;

        mindMapView.setCoordinates(x1,y1,x-x1,y-y1);
        for(ElementPainter painter: mindMapView.getPainters()){
            TermElement element = (TermElement) painter.getElement();

            if(selectionRec.intersects(element.getPosition().getFirst(),element.getPosition().getSecond(),element.getSize().getFirst(),element.getSize().getSecond())){
                    mindMapView.getSelectedElements().select(element);
            }
            else mindMapView.getSelectedElements().unselect(element);
        }
    }

}
