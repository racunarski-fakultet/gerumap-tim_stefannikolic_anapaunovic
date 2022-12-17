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
import dsw.rumap.app.msggenerator.Problem;

import javax.swing.*;


public class AddTermState implements State {

    @Override
    public void execute() {

    }

    @Override
    public void stateMousePressed(int x, int y, MindMapView mindMapView) {
        MindMap mindMap = mindMapView.getModel();

        x -= mindMapView.getTranslate().getFirst();
        y -= mindMapView.getTranslate().getSecond();

        x /= mindMapView.getScale();
        y /= mindMapView.getScale();

        String name = JOptionPane.showInputDialog("Unesite naziv pojma:");
        if(name == null)
            return;
        else if(name.isEmpty()){
            AppCore.getInstance().getMsgGenerator().createMessage(Problem.NAME_CANNOT_BE_EMPTY);
            return;
        }
        else if(!mindMap.checkName(name)){
            AppCore.getInstance().getMsgGenerator().createMessage(Problem.NAME_ALREADY_EXISTS);
            return;
        }

        Element element = new TermElement(name, mindMap, x-30, y-15);
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
