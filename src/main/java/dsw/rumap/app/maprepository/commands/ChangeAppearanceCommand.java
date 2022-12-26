package dsw.rumap.app.maprepository.commands;

import dsw.rumap.app.maprepository.implementation.Element;
import dsw.rumap.app.maprepository.implementation.elements.Pair;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class ChangeAppearanceCommand implements Command{

    private Integer newColor;
    private Integer newStroke;
    private HashMap<Element, Pair<Integer, Integer>> map;

    public ChangeAppearanceCommand(Integer newColor, Integer newStroke, List<Element> selected) {
        this.newColor = newColor;
        this.newStroke = newStroke;
        map = new HashMap<>();
        for (Element e :
                selected) {
            map.put(e, new Pair<>(e.getColor(), e.getStroke()));
        }
    }

    @Override
    public void doCommand() {
        map.forEach((element, colorStrokePair) -> {
            element.setColor(newColor);
            element.setStroke(newStroke);
        });
    }

    @Override
    public void undoCommand() {
        map.forEach(((element, colorStrokePair) -> {
            element.setColor(colorStrokePair.getFirst());
            element.setStroke(colorStrokePair.getSecond());
        }));
    }
}
