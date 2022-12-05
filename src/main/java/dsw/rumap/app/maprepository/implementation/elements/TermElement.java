package dsw.rumap.app.maprepository.implementation.elements;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import dsw.rumap.app.maprepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
@Getter
@Setter
public class TermElement extends Element {

    private Pair<Integer, Integer> size;
    private Pair<Integer, Integer> position;

    public TermElement(String name, MapNode parent) {
        super(name, parent);
    }

    public TermElement(MapNode parent) {
        super(parent);

    }


}
