package dsw.rumap.app.maprepository.implementation.elements;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelationElement extends Element {

    private TermElement fromTerm;
    private TermElement toTerm;

    public RelationElement(String name, MapNode parent) {
        super(name, parent);
    }

    public RelationElement(MapNode parent) {
        super(parent);
        setStroke(3);
    }
}
