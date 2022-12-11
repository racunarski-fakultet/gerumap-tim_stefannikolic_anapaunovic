package dsw.rumap.app.gui.swing.state.stateimpl;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.state.State;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.gui.swing.view.painters.ElementPainter;
import dsw.rumap.app.gui.swing.view.painters.TermPainter;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.MindMap;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;

import javax.swing.*;


public class AddTermState implements State {

    @Override
    public void execute() {

    }

    @Override
    public void stateMousePressed(int x, int y, MindMapView mindMapView, int clickCount) {
        MindMap mindMap = mindMapView.getModel();
        Element element = new TermElement(mindMap, x, y);
        ElementPainter painter = new TermPainter(element);
        mindMapView.addPainter(painter);
        //TODO treba da se prodje kroz listu cvorova u stablu da se nadje taj i da se doda
        //MainFrame.getInstance().getMapTree().addChild(mindMap, element);
    }


    @Override
    public void stateMouseReleased(int x, int y, MindMapView mindMapView) {

    }

    @Override
    public void stateMouseDragged(int x, int y, MindMapView mindMapView) {

    }
}
