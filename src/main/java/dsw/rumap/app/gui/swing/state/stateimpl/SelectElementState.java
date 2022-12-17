package dsw.rumap.app.gui.swing.state.stateimpl;

import dsw.rumap.app.gui.swing.state.State;
import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.gui.swing.view.painters.ElementPainter;
import dsw.rumap.app.gui.swing.view.painters.RelationPainter;
import dsw.rumap.app.maprepository.implementation.elements.MapSelectionModel;
import dsw.rumap.app.maprepository.implementation.elements.RelationElement;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;
import java.awt.*;

public class SelectElementState implements State {

    private int startX;
    private int startY;
    private boolean found = false;
    
    @Override
    public void execute() {}

    @Override
    public void stateMousePressed(int x, int y, MindMapView mindMapView) {

        x = mindMapView.correctMouseX(x);
        y = mindMapView.correctMouseY(y);

        MapSelectionModel selectedElements = mindMapView.getMapSelectionModel();
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
        if(!found){
            mindMapView.setSelectionRec(x,y,0,0);
            selectedElements.clear();
            this.startX = x;
            this.startY = y;
        }
    }

    @Override
    public void stateMouseDragged(int x, int y, MindMapView mindMapView) {

        x = mindMapView.correctMouseX(x);
        y = mindMapView.correctMouseY(y);

        if (found)
            return;

        if(x < startX && y < startY)
            mindMapView.setSelectionRec(x, y, startX-x, startY-y);
        else if(x < startX)
            mindMapView.setSelectionRec(x, startY, startX-x,y-startY);
        else if(y < startY)
            mindMapView.setSelectionRec(startX, y,x-startX, startY-y);
        else mindMapView.setSelectionRec(startX, startY, x-startX, y-startY);

        for(ElementPainter painter: mindMapView.getPainters()){
            if(painter instanceof RelationPainter) {
                RelationElement relationElement = (RelationElement) painter.getElement();
                if (mindMapView.getSelectionRec().intersectsLine(relationElement.getStart().getFirst(), relationElement.getStart().getSecond(), relationElement.getEnd().getFirst(), relationElement.getEnd().getSecond()))
                    mindMapView.getMapSelectionModel().select(relationElement);
                else mindMapView.getMapSelectionModel().unselect(relationElement);
            }

            else {
                TermElement termElement = (TermElement) painter.getElement();
                if (mindMapView.getSelectionRec().intersects(termElement.getPosition().getFirst(), termElement.getPosition().getSecond(), termElement.getSize().getFirst(), termElement.getSize().getSecond()))
                    mindMapView.getMapSelectionModel().select(termElement);
                else mindMapView.getMapSelectionModel().unselect(termElement);
            }
        }
    }
    
    @Override
    public void stateMouseReleased(int x, int y, MindMapView mindMapView) {
        mindMapView.setSelectionRec(0,0,0,0);
    }
}
