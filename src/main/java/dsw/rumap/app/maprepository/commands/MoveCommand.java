package dsw.rumap.app.maprepository.commands;

import dsw.rumap.app.gui.swing.state.stateimpl.MoveElementState;
import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.Pair;
import dsw.rumap.app.maprepository.implementation.elements.TermElement;

import java.util.HashMap;
import java.util.List;

public class MoveCommand implements Command{

    private HashMap<Element, Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> map;

    public MoveCommand(List<Element> selected, int deltaX, int deltaY){
        map = new HashMap<>();
        for (Element e :
                selected) {
            if(e instanceof TermElement) {
                TermElement termElement = (TermElement) e;
                map.put(e, new Pair(new Pair(termElement.getPosition().getFirst() - deltaX, termElement.getPosition().getSecond() - deltaY), termElement.getPosition()));
            }
        }
    }

    @Override
    public void doCommand() {
        map.forEach(((element, coordinates) -> {
            TermElement termElement = (TermElement) element;
            termElement.setPosition(coordinates.getSecond());
        }));
    }

    @Override
    public void undoCommand() {
        map.forEach(((element, coordinates) -> {
            TermElement termElement = (TermElement) element;
            termElement.setPosition(coordinates.getFirst());
        }));
    }
}
