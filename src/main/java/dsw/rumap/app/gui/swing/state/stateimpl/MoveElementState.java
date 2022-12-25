package dsw.rumap.app.gui.swing.state.stateimpl;

import dsw.rumap.app.AppCore;
import dsw.rumap.app.gui.swing.state.State;
import dsw.rumap.app.gui.swing.view.MindMapView;
import dsw.rumap.app.maprepository.commands.Command;
import dsw.rumap.app.maprepository.commands.MoveCommand;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;

public class MoveElementState implements State {

    private int startX;
    private int startY;
    private int newStartX;
    private int newStartY;

    @Override
    public void execute() {}

    @Override
    public void stateMousePressed(int x, int y, MindMapView mindMapView) {
        startX = x;
        startY = y;
        newStartX = x;
        newStartY = y;
    }

    @Override
    public void stateMouseDragged(int x, int y, MindMapView mindMapView) {

        int moveX = x - newStartX;
        int moveY = y - newStartY;

        for(Element selected: mindMapView.getMapSelectionModel().getSelected()){
            if(selected instanceof TermElement) {
                TermElement selectedTerm = (TermElement) selected;
                selectedTerm.setPosition(selectedTerm.getPosition().getFirst() + moveX, selectedTerm.getPosition().getSecond() + moveY);
            }
            newStartX = x;
            newStartY = y;
        }
    }

    @Override
    public void stateMouseReleased(int x, int y, MindMapView mindMapView) {

        Command newCommand = new MoveCommand(mindMapView.getMapSelectionModel().getSelected(), x-startX, y-startY);
        AppCore.getInstance().getMapRepository().getCommandManager().addCommand(newCommand);
    }
}