package dsw.rumap.app.gui.swing.state.stateimpl;

import dsw.rumap.app.gui.swing.state.State;
import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.gui.swing.view.painters.TermPainter;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.Pair;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;

import javax.swing.*;


public class AddTermState implements State {

    @Override
    public void execute() {

    }

    @Override
    public void stateMousePressed(int x, int y, MindMapView mapView) {

        Element element = new TermElement(mapView.getModel()); //automatski se dodaje u listu dece
        ((TermElement)element).setPosition(new Pair<>(x,y)); // ili u konstktoru
        TermPainter painter = new TermPainter(element,mapView);


    }


    @Override
    public void stateMouseReleased() {

    }

    @Override
    public void stateMouseDragged() {

    }
}
