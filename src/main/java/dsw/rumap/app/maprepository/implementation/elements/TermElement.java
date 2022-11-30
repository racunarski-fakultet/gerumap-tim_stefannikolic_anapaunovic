package dsw.rumap.app.maprepository.implementation.elements;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;

import java.awt.*;

public class TermElement extends Element {

    private Dimension size;
    private Point position;

    public TermElement(String name, MapNode parent) {
        super(name, parent);
    }

    public TermElement(MapNode parent) {
        super(parent);
    }
}
