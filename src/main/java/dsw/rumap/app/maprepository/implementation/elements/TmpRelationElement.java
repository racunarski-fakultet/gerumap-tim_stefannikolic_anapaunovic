package dsw.rumap.app.maprepository.implementation.elements;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class TmpRelationElement extends Element {

    private TermElement fromTerm;
    private TermElement toTerm;
    private Pair<Integer, Integer> start;
    private Pair<Integer, Integer> end;

    public TmpRelationElement(String name, MapNode parent) {
        super(name, parent);
    }

    public TmpRelationElement(MapNode parent, Element fromTerm, int x, int y) {
        super(parent);
        this.start = new Pair<>(x, y);
        this.end = start;
        this.fromTerm = (TermElement) fromTerm;
        setColor(Color.GREEN);
        setStroke(3);
    }

    public void setEnd(Pair<Integer, Integer> end){
        this.end = end;
        notify(null);
    }
}
