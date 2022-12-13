package dsw.rumap.app.gui.swing.state.stateimpl;

import dsw.rumap.app.gui.swing.state.State;
import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.gui.swing.view.painters.ElementPainter;
import dsw.rumap.app.gui.swing.view.painters.TermPainter;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.Pair;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MoveElementState implements State {

    private Element element;
    private boolean oneSelected = false;
    private boolean found = false;
    private boolean conditions = false;
    private int moveX;
    private int moveY;
    private int startX;
    private int startY;

    @Override
    public void execute() {

    }
    @Override
    public void stateMousePressed(int x, int y, MindMapView mindMapView, int clickCount) {
        if(mindMapView.getSelectedElements().getSelected().size() == 1)
            oneSelected = true;
        else oneSelected = false;

        if(oneSelected == true){
            for(ElementPainter painter: mindMapView.getPainters()){
                if(painter.elementAt(new Point(x,y))){
                    if(mindMapView.getSelectedElements().isSelected(painter.getElement())){
                        element = painter.getElement();
                        found = true;
                        break;
                    }
                }
            }
        }
        else if(oneSelected == false){
            //da li ima selektovanih, da li je dupli klik, da li sam u pravougaoniku
            //mindMapView.getLastRactangle().contains(x,y)
            if(!mindMapView.getSelectedElements().getSelected().isEmpty()){
                conditions = true;
                startX = x;
                startY = y;
            }
            else conditions = false;
        }
    }
    @Override
    public void stateMouseReleased(int x, int y, MindMapView mindMapView) {
        if(oneSelected == false){
            if(conditions == true){
                moveAllElements(x,y,mindMapView);
            }
        }
        else if(found == true){
            moveOneElement(x,y);
            found = false;

        }

    }
    @Override
    public void stateMouseDragged(int x, int y, MindMapView mindMapView) {
        if(oneSelected == false){
            if(conditions == true){
                moveAllElements(x,y,mindMapView);
            }
        }
        else if(found == true){
            moveOneElement(x,y);
        }
    }
    public void moveOneElement(int x, int y){
        ((TermElement)element).setPosX(x);
        ((TermElement)element).setPosY(y,true);
    }

    public void moveAllElements(int x, int y, MindMapView mindMapView){
        moveX = x - startX;
        moveY = y - startY;
        List<Element> list =  mindMapView.getSelectedElements().getSelected();
        for(Element selected: list){

            TermElement selectedTerm = (TermElement)selected;
            selectedTerm.setPosX(selectedTerm.getPosition().getFirst()+moveX);
            if(list.indexOf(selected) == list.size()-1){
                selectedTerm.setPosY(selectedTerm.getPosition().getSecond()+moveY,true);
                startX = x;
                startY = y;
            }
            else selectedTerm.setPosY(selectedTerm.getPosition().getSecond()+moveY,false);
            //mindMapView.setLastCoordinates((int) (mindMapView.getLastRactangle().getX() + moveX), (int) (mindMapView.getLastRactangle().getY() + moveY));
        }
    }

}
