package dsw.rumap.app.maprepository.implementation;

import dsw.rumap.app.maprepository.composite.MapNode;

public class Element extends MapNode {
    public Element(String name, MapNode parent) {
        super(name, parent);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        this.notify(this);
    }
}
