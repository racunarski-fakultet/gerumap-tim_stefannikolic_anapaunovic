package dsw.rumap.app.maprepository.implementation;

import dsw.rumap.app.maprepository.composite.MapNode;

import dsw.rumap.app.observer.IPublisher;
import dsw.rumap.app.observer.ISubscriber;
import dsw.rumap.app.maprepository.composite.MapNodeC;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
public abstract class Element extends MapNode {

    private Integer stroke;
    private Color color;

    public Element(String name, MapNode parent) {
        super(name, parent);
        //((MapNodeC) parent).add(this);
    }

    public Element(MapNode parent){

        this("Element" + ((MapNodeC)parent).makeNameForChild(), parent);
        //((MapNodeC) parent).add(this);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        this.notify(this);
    }

    public void setStroke(Integer stroke) {
        this.stroke = stroke;
        this.notify(this);
    }

    public void setColor(Color color) {
        this.color = color;
        this.notify(this);
    }
}
