package dsw.rumap.app.maprepository.implementation;

import dsw.rumap.app.maprepository.composite.MapNode;
import dsw.rumap.app.maprepository.composite.MapNodeC;

public class Element extends MapNode {
    public Element(String name, MapNode parent) {
        super(name, parent);
    }

    public Element(MapNode parent){
        this("Element" + ((MapNodeC)parent).makeNameForChild(), parent);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        this.notify(this);
    }
}
