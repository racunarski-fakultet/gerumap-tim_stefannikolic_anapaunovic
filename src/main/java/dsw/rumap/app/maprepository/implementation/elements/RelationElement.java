package dsw.rumap.app.maprepository.implementation.elements;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class RelationElement extends Element {

    private TermElement fromTerm;
    private TermElement toTerm;
    private Pair<Integer, Integer> start;
    private Pair<Integer, Integer> end;

    public RelationElement(String name, MapNode parent) {
        super(name, parent);
    }

    public RelationElement(MapNode parent, Element fromTerm, int x, int y) {
        super(parent);
        this.start = new Pair<>(x, y);
        this.end = start;
        this.fromTerm = (TermElement) fromTerm;
        setColor(Color.GREEN.getRGB());
        setStroke(3);
        type = "RelationElement";
    }

    public void setEnd(Pair<Integer, Integer> end){
        this.end = end;
        notify(null);
    }
}
