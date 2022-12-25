package dsw.rumap.app.maprepository.implementation.elements;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
@Getter
public class TermElement extends Element {

    private Pair<Integer, Integer> size;
    private Pair<Integer, Integer> position;

    public TermElement(String name, MapNode parent) {
        super(name, parent);
    }

    public TermElement(String name, MapNode parent, int x, int y) {
        this(name, parent, new Pair(x, y));
    }

    public TermElement(String name, MapNode parent, Pair<Integer, Integer> position) {
        super(name, parent);
        this.setStroke(3);
        this.position = position;
        size = new Pair(this.getName().length()*7+60, 30);
        setColor(Color.CYAN);
    }

    public void setPosition(Integer x, Integer y) {
        position.setFirst(x);
        position.setSecond(y);
        this.notify(this);
    }

    public void setSize(Pair<Integer, Integer> size) {
        this.size = size;
        this.notify(this);
    }


    public void setPosition(Pair<Integer, Integer> position) {
        this.position = position;
        this.notify(this);
    }
}
