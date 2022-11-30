package dsw.rumap.app.maprepository.implementation.elements;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;

public class RelationElement extends Element {

    private TermElement fromTerm;
    private TermElement toTerm;

    public RelationElement(String name, MapNode parent) {
        super(name, parent);
    }

    public RelationElement(MapNode parent) {
        super(parent);
    }
}
