package dsw.rumap.app.gui.swing.state.stateimpl;

import dsw.rumap.app.gui.swing.state.State;
import dsw.rumap.app.gui.swing.view.MainFrame;
import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.gui.swing.view.painters.ElementPainter;
import dsw.rumap.app.maprepository.commands.AddElementCommand;
import dsw.rumap.app.maprepository.commands.Command;
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

        for (ElementPainter ep :
                mindMapView.getPainters()) {
            if(ep.elementAt(new Point(x, y))) {
                Element tmpElement = new RelationElement(mindMap, ep.getElement(), x, y);
                //AppCore.getInstance().getMapRepository().addChild(mindMap, tmpElement);
                Command newCommand = new AddElementCommand(mindMap, tmpElement);
                MainFrame.getInstance().getProjectView().getCurrentMindMapView().getModel().getCommandManager().addCommand(newCommand);
                //ElementPainter painter = new RelationPainter(tmpElement);
                //mindMapView.addPainter(painter);
                currentEP = mindMapView.getPainters().get(mindMapView.getPainters().size()-1);;
                break;
            }
        }
    }

    @Override
    public void stateMouseDragged(int x, int y, MindMapView mindMapView) {

        if(currentEP == null) return;
        ((RelationElement) currentEP.getElement()).setEnd(new Pair<>(x, y));
    }

    @Override
    public void stateMouseReleased(int x, int y, MindMapView mindMapView) {

        if(currentEP == null) return;
        boolean found = false;
        for (ElementPainter ep :
                mindMapView.getPainters()) {
            if(ep.getElement() instanceof RelationElement) continue;
            if(ep.elementAt(new Point(x, y))) {
                ((RelationElement) currentEP.getElement()).setEnd(new Pair<>(x, y));
                ((RelationElement) currentEP.getElement()).setToTerm((TermElement) ep.getElement());
                found = true;
                break;
            }
        }

        if(!found || ((RelationElement) currentEP.getElement()).getFromTerm() == ((RelationElement) currentEP.getElement()).getToTerm()){
            //mindMapView.removePainter(currentEP);
            //mindMapView.getModel().delete(currentEP.getElement());
            MainFrame.getInstance().getProjectView().getCurrentMindMapView().getModel().getCommandManager().permanentUndoCommand();
        }
        currentEP = null;
    }

}
